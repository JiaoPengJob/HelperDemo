package com.soap.jiaop.webviewandjsdemo.activity;

import android.content.Context;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.soap.jiaop.webviewandjsdemo.R;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.addJavascriptInterface(new JsInterface(this),
                "AndroidWebView");
        webView.loadUrl("");
    }

    /**
     * js交互借口
     */
    private class JsInterface {
        private Context mContext;

        public JsInterface(Context context) {
            this.mContext = context;
        }

        // 在js中调用window.AndroidWebView.showInfoFromJs(name)，便会触发此方法。
        @SuppressWarnings("unused")
        @JavascriptInterface
        public void showInfoFromJs(String FuellingRecordID, String RecordInfor) {

        }
    }
}
