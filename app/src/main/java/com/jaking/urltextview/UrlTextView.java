package com.jaking.urltextview;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/10/21.
 */

public class UrlTextView extends TextView {
    public Context ctx;

    public UrlTextView(Context context) {
        this(context, null);
    }

    public UrlTextView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public UrlTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ctx = context;
        init();
    }

    private void init() {
        //this.setAutoLinkMask(Linkify.ALL);
        this.setClickable(true);
    }

    public void setHtml(String html) {
        Spanned spannedHtml = Html.fromHtml(html);
        SpannableStringBuilder style = new SpannableStringBuilder(spannedHtml);

        int end = spannedHtml.length();
        URLSpan[] urls = spannedHtml.getSpans(0, end, URLSpan.class);
        style.clearSpans();// should clear old spans  
        for (URLSpan url : urls) {
            MyURLSpan myURLSpan = new MyURLSpan(url.getURL(), ctx);
            style.setSpan(myURLSpan, spannedHtml.getSpanStart(url), spannedHtml
                    .getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        this.setText(style);
        this.setMovementMethod(LinkMovementMethod.getInstance());

    }

    private static class MyURLSpan extends ClickableSpan {

        private String mUrl;
        private Context ctx;

        MyURLSpan(String url, Context ctx) {
            mUrl = url;
            this.ctx = ctx;
        }

        @Override
        public void onClick(View widget) {
            Toast.makeText(ctx, mUrl, Toast.LENGTH_SHORT).show();
            widget.setBackgroundColor(Color.parseColor("#00555555"));
        }
    }
}