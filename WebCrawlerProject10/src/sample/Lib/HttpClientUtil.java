package sample.Lib;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class HttpClientUtil {
    private Logger log = Logger.getLogger(HttpClientUtil.class);
    public static Map<String, String> valueUrlDecode(Map<String, String> source) throws UnsupportedEncodingException {
        for (Map.Entry<String, String> entry : source.entrySet()) {
            if (!entry.getValue().equals("")) {
                entry.setValue(URLDecoder.decode(entry.getValue(), "utf-8"));
            }
        }
        return source;
    }

    public String getResponseText(CloseableHttpResponse response) throws IOException {
        String ServerResponeString = "";
        HttpEntity entity = response.getEntity();
        log.info("-------------------getResponseText:begin-------------------");
        // 打印响应状态
        log.info(response.getStatusLine());
        if (entity != null) {
            // 打印响应内容长度
            log.info("Response content length: " + entity.getContentLength());
            // 打印响应内容
            ServerResponeString = EntityUtils.toString(entity,"gb2312");
            log.info("Response content: " + ServerResponeString);
        }
        log.info("----------------getResponseText:end--------------------");
        return ServerResponeString;
    }

    public String getHttpPostResult(String url, Map<String, String> senddata, boolean encode) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String ServerResponeString = "";
        try {
            // 创建httpget.
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            String postData = "";
            for (Map.Entry<String, String> stringEntry : senddata.entrySet()) {
                if (encode) {
                    nvps.add(new BasicNameValuePair(stringEntry.getKey(), URLEncoder.encode(stringEntry.getValue(), "utf-8")));
                    postData += stringEntry.getKey() + "=" + URLEncoder.encode(stringEntry.getValue(), "utf-8") + "&";
                } else {
                    nvps.add(new BasicNameValuePair(stringEntry.getKey(), stringEntry.getValue()));
                    postData += stringEntry.getKey() + "=" + stringEntry.getValue();
                }
            }
            ;
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            System.out.println("executing request " + httpPost.getURI());
            System.out.println("executing request post " + postData);

            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                // 获取响应实体
                ServerResponeString = getResponseText(response);
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ServerResponeString;
    }

    public String getHttpPostResult(String url, Map<String, String> senddata) {
        return getHttpPostResult(url, senddata, false);
    }


    public String getHttpGetResult(String url) {
        BasicCookieStore basicCookieStore=new BasicCookieStore();
        return getHttpGetResult(url, basicCookieStore,0);
    }

    public BasicCookieStore getHttpGetCookie(String url) throws IOException {
        BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();

        //HttpGet httpget = new HttpGet("https://someportal/");
        //HttpGet httpget = new HttpGet("https://www.oschina.net/home/login");
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response1 = httpclient.execute(httpget);
        try {
            HttpEntity entity = response1.getEntity();
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response1.getEntity().getContent()));
            System.out.println("--------------------Next will show something else");
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("Login form get: " + response1.getStatusLine());
            EntityUtils.consume(entity);
            System.out.println("Initial set of cookies:");
            List<Cookie> cookies = cookieStore.getCookies();
            if (cookies.isEmpty()) {
                System.out.println("None");
            } else {
                for (int i = 0; i < cookies.size(); i++) {
                    System.out.println("- " + cookies.get(i).toString());
                }
            }
        } finally {
            response1.close();
            return cookieStore;
        }
    }
    public String getHttpGetResult(String url,BasicCookieStore cookieStore, int retryTime) {

        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(20000)
                .build();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig)
                .setDefaultCookieStore(cookieStore)
                .build();

        String ServerResponeString = "";

        //log.info("-------------------getHttpGetResult:begin-------------------");
        try {
            // 创建httpget.
            HttpGet httpget = new HttpGet(url);
            //httpget.
            //System.out.println("executing request " + httpget.getURI());
            // 执行get请求.
            RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).build();
            httpget.setConfig(requestConfig);
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                ServerResponeString = getResponseText(response);
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            //log.info("ClientProtocolException："+e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
            //log.info("ParseException："+e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            log.info("IOException：" + e.getMessage());
            log.info("-------------------链接超时重试:begin:" + retryTime + "-------------------");
            if (retryTime > 0) {
                ServerResponeString = getHttpGetResult(url, cookieStore,retryTime - 1);
            }
            log.info("-------------------链接超时重试:end" + retryTime + "-------------------");
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                log.info("finally:IOException：" + e.getMessage());
                e.printStackTrace();
            }
        }
        //log.info("ServerResponeString："+ ServerResponeString);
        //log.info("-------------------getHttpGetResult:end-------------------");
        return ServerResponeString;
    }
}
