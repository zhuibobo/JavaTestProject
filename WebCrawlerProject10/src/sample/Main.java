package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import sample.Lib.HttpClientUtil;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        HttpClientUtil httpClientUtil=new HttpClientUtil();
        //BasicCookieStore basicCookieStore=httpClientUtil.getHttpGetCookie("http://www.qichacha.com/");
        //basicCookieStore.getCookies().get(0)
        BasicCookieStore basicCookieStore=new BasicCookieStore();
        /*BasicClientCookie cookie0=new BasicClientCookie("PHPSESSID","8fdpmcec72gj96ajcnt49brfv6");
        cookie0.setDomain("www.qichacha.com");
        cookie0.setPath("/");
        BasicClientCookie cookie1=new BasicClientCookie("_umdata","0823A424438F76ABB6F91B8B2E619F54FED4A458A695669701BDDF3F7DBEB560FCD0F7DE9582F6811B76576A5E061DED375D9C126697B09D20796F704835AC998DAFF4A6885CB5FB56E8A030497864FD6124A946E159DFD900B3235789DCB5F6EEA6B27956624A9D");
        cookie1.setDomain("www.qichacha.com");
        cookie1.setPath("/");
        BasicClientCookie cookie2=new BasicClientCookie("acw_tc","AQAAAG41sjgkXg0AAOYR2kmZnkPoISYv");
        cookie2.setDomain("www.qichacha.com");
        cookie2.setPath("/");
        BasicClientCookie cookie3=new BasicClientCookie("CNZZDATA1254842228","1496100639-1521464003-%7C1521464003");
        cookie3.setDomain("www.qichacha.com");
        cookie3.setPath("/");
        BasicClientCookie cookie4=new BasicClientCookie("hasShow","1");
        cookie4.setDomain("www.qichacha.com");
        cookie4.setPath("/");
        BasicClientCookie cookie5=new BasicClientCookie("Hm_lpvt_3456bee468c83cc63fb5147f119f1075","1521467517");
        cookie5.setDomain("qichacha.com");
        cookie5.setPath("/");
        BasicClientCookie cookie6=new BasicClientCookie("Hm_lvt_3456bee468c83cc63fb5147f119f1075","1521465369,1521467517");
        cookie6.setDomain("qichacha.com");
        cookie6.setPath("/");
        BasicClientCookie cookie7=new BasicClientCookie("UM_distinctid","1623e6721b435d-0cf6bc8580e49b8-192a72-1fa400-1623e6721b52e0");
        cookie7.setDomain("qichacha.com");
        cookie7.setPath("/");
        BasicClientCookie cookie8=new BasicClientCookie("zg_de1d1a35bfa24ce29bbf2c7eb17e6c4f","%7B%22sid%22%3A%201521467516442%2C%22updated%22%3A%201521467516442%2C%22info%22%3A%201521465368969%2C%22superProperty%22%3A%20%22%7B%7D%22%2C%22platform%22%3A%20%22%7B%7D%22%2C%22utm%22%3A%20%22%7B%7D%22%2C%22referrerDomain%22%3A%20%22%22%7D");
        cookie8.setDomain("qichacha.com");
        cookie8.setPath("/");
        BasicClientCookie cookie9=new BasicClientCookie("zg_did","%7B%22did%22%3A%20%221623e6721842a3-098588bb235f18-192a72-1fa400-1623e67218597b%22%7D");
        cookie9.setDomain("qichacha.com");
        cookie9.setPath("/");
        basicCookieStore.addCookie(cookie0);
        basicCookieStore.addCookie(cookie1);
        basicCookieStore.addCookie(cookie2);
        basicCookieStore.addCookie(cookie3);
        basicCookieStore.addCookie(cookie4);
        basicCookieStore.addCookie(cookie5);
        basicCookieStore.addCookie(cookie6);
        basicCookieStore.addCookie(cookie7);
        basicCookieStore.addCookie(cookie8);
        basicCookieStore.addCookie(cookie9);
        System.out.println(httpClientUtil.getHttpGetResult("https://www.tianyancha.com/search?key=%E7%9C%9F%E6%96%B0%E7%A7%91%E6%8A%80",null,0));*/
        System.out.println(httpClientUtil.getHttpGetResult("http://data.eastmoney.com/stockdata/002322.html"));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
