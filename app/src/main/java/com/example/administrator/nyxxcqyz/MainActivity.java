package com.example.administrator.nyxxcqyz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例化WebView对象
        WebView webview = new WebView(this);
        //设置WebView属性，能够执行Javascript脚本
        webview.getSettings().setJavaScriptEnabled(true);
        try {
            //设置打开的页面地址
            Intent i = getIntent();
            String userId = i.getStringExtra("userId");
            String password = i.getStringExtra("password");
            webview.loadUrl("http://"+getString(R.string.ip)+":8080/zhccnx/android.jsp?userId="+userId+"&password="+password);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        setContentView(webview);

        webview.setWebViewClient(new WebViewClient()
        {
            public boolean shouldOverrideUrlLoading(WebView view,String url)
            {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
