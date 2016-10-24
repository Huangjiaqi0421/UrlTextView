package com.jaking.urltextview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.widget.TextView;

public class WebContentActivity extends Activity implements OnClickListener {
    public static final String ACTION_GOBACKCLOSE = "gobackClose";
    private ProgressWebView mContentWebView;
    private TextView back;
    private TextView title;
    private TextView closeBtn;
    private String topTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initView() {
        back = (TextView) findViewById(R.id.back);
        back.setOnClickListener(this);
        title = (TextView) findViewById(R.id.title);
        closeBtn = (TextView) findViewById(R.id.close);
        closeBtn.setOnClickListener(this);
        mContentWebView = (ProgressWebView) findViewById(R.id.webview);
        WebSettings setting = mContentWebView.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        setting.setAppCacheEnabled(true);
        String url=getIntent().getStringExtra("url");
        mContentWebView.loadUrl(url);
        mContentWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
                    goBack();
                    return true;
                }

                return false;
            }
        });

        mContentWebView.addJavascriptInterface(new JavaScriptObject(this), "myObj");
        if (TextUtils.isEmpty(topTitle)) {
            mContentWebView.setTitleView(title);
        }


    }


    public class JavaScriptObject {
        public Context mContxt;

        public JavaScriptObject(Context mContxt) {
            this.mContxt = mContxt;
        }

        @JavascriptInterface
        public void openDetail(String id, String type) {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                goBack();
                break;
            case R.id.close:
                close();
                break;
            default:
                break;
        }

    }

    private void goBack() {
        if (mContentWebView.canGoBack()) {
            mContentWebView.goBack();
            closeBtn.setVisibility(View.VISIBLE);
            title.setMaxEms(5);
        } else {
            close();
        }
    }

    private void close() {
        WebContentActivity.this.finish();

    }

    @Override
    protected void onDestroy() {
           /*
        java.lang.IllegalArgumentException: Receiver not registered: android.widget.ZoomButtonsController$1@534a0f08
        at android.app.LoadedApk.forgetReceiverDispatcher(LoadedApk.java:657)
        at android.app.ContextImpl.unregisterReceiver(ContextImpl.java:1347)
        */
        mContentWebView.getSettings().setBuiltInZoomControls(true);
        super.onDestroy();
    }
}
