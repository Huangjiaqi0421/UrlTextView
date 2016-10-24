# UrlTextView
可识别网页的TextView，链接点击事件自定义

# 使用方法

```Java
        String url = "这是一个简单的可识别网页链接的demo。<a href=\"https://github.com/S-MILE-S/UrlTextView\">click here to get code</a>, code is very simple.or点击<a href=\"http://www.google.com\">这里</a>打开GOOGLE";
        textView.setHtml(url);
```

MyUrlSpan 中可以设置链接点击事件

```Java
        @Override
        public void onClick(View widget) {
//            Toast.makeText(ctx, mUrl, Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(ctx,WebContentActivity.class);
            intent.putExtra("url",mUrl);
            ctx.startActivity(intent);
        }
```
