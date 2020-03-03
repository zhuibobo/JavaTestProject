package Elasticsearch14;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2020/2/13
 * To change this template use File | Settings | File Templates.
 */
public class ElasticSearchConnect {
    public RestHighLevelClient getClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.3.195", 9200, "http")));
        return client;
    }
}
