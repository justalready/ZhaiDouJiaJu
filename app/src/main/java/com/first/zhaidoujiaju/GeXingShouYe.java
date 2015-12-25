package com.first.zhaidoujiaju;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

/**
 * Created by Administrator on 15-10-8.
 */
public class GeXingShouYe extends Activity {


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            Intent intent3 = new Intent(GeXingShouYe.this, MainActivity.class);
            startActivity(intent3);
            finish();
        }

    };
    public ImageView iamge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gexingshouye);
        iamge = ((ImageView) findViewById(R.id.gexingshouye));
        handler.sendEmptyMessageDelayed(0, 2000);
    }
}
