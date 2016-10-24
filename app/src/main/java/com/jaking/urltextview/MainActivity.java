package com.jaking.urltextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    UrlTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (UrlTextView) findViewById(R.id.textview);
        String url = "这是一个简单的可识别网页链接的demo。<a href=\"https://github.com/S-MILE-S/UrlTextView\">click here to get code</a>, code is very simple.or点击<a href=\"http://www.google.com\">这里</a>打开GOOGLE";
        textView.setHtml(url);
    }
}
