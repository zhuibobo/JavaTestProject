package com.example.myapplication18.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication18.R;

public class WebViewTest001Activity extends AppCompatActivity {

    WebView webView;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_test001);

        webView = new WebView(this);

        //WebChromeClient webChromeClient=new WebChromeClient();

        webView.setWebViewClient(new WebViewClient() {
            //设置在webView点击打开的新网页在当前界面显示,而不跳转到新的浏览器中
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                //return super.onJsAlert(view, url, message, result);
                midToast("onJsAlert:"+message,Toast.LENGTH_LONG);
                //return super.onJsAlert(view, url, message, result);
                result.cancel();	//一定要cancel，否则会出现各种奇怪问题
                return true;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);  //设置WebView属性,运行执行js脚本
        webView.loadUrl("http://192.168.3.206:8055/html/index.html"); //调用loadUrl方法为WebView加入链接
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.clearCache(true);
        setContentView(webView);
    }

    void midToast(String str, int showTime)
    {
        Toast toast = Toast.makeText(WebViewTest001Activity.this, str, showTime);
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL , 0, 0);  //设置显示位置
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.BLACK);     //设置字体颜色
        toast.show();
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }

        }
    }
}
