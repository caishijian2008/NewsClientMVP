package com.xiaowu.news;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SearchNewsDetailsActivity extends Activity {

    private static final String TAG = "main";
    private WebView webView;
    private ProgressDialog dialog;
    private String url = "http://news.baidu.com/ns?word=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_news_details);
        initView();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String s = bundle.getString("text");
        Log.i(TAG, "bundle.getString(\"text\")-->"+s);

        webView.loadUrl(url+s);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });

        //启用JavaScript和设置缓存
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    closeDialog();
                } else {
                    openDialog(newProgress);
                }
            }

            private void openDialog(int newProgress) {
                if (dialog == null) {
                    dialog = new ProgressDialog(SearchNewsDetailsActivity.this);
                    dialog.setTitle("正在加载中...");
                    dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    dialog.setProgress(newProgress);
                    dialog.show();
                    dialog.setCanceledOnTouchOutside(false);
                } else {
                    dialog.setProgress(newProgress);
                }
            }

            private void closeDialog() {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                    dialog = null;
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            } else {
                SearchNewsDetailsActivity.this.finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.webView1);
    }

}
