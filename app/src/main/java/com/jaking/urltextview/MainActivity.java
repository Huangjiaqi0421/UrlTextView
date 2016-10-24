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
        String url = "This is a page with lots of URLs. <a href=\"http://jb51.net\">jb51.net</> " +
                "This left is a very good blog. There are so many great blogs there.You can find what" +
                "you want in that blog. <a href=\"http://www.baidu.com\">超链接点击事件</a> The Next Link is <a href=\"http://www.google.com.hk\">Google HK</a>";
        textView.setHtml(url);
    }
}
