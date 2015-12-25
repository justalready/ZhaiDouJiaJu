package com.first.zhaidoujiaju.Fragment;


import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.first.zhaidoujiaju.Adapter.CustomAdapter;
import com.first.zhaidoujiaju.R;

public class ListViewXiangQing extends Activity implements CustomAdapter.SendId, View.OnClickListener {
    public WebView web;
    private String id;

    @Override
    public void send(String s) {
        this.id=s;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list_view_xiang_qing);
        web = ((WebView) findViewById(R.id.webView));
        findViewById(R.id.fanhui).setOnClickListener(this);
         findViewById(R.id.fenxiang).setOnClickListener(this);
        web.loadUrl("http://www.zhaidou.com/article/articles/"+609+"?open=app");


        web.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && web.canGoBack()) {  //表示按返回键 时的操作
                        web.goBack();   //后退
                        return true;    //已处理
                    }
                }
                return false;
            }
        });

        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }


    @Override
    public void onClick(View view) {
         switch (view.getId()){
             case R.id.fanhui:
                 ListViewXiangQing.this.finish();
                break;
             case R.id.fenxiang:

                 break;
         }
    }
}
