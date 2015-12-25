package com.first.zhaidoujiaju;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.first.zhaidoujiaju.Fragment.BlankFragment;
import com.first.zhaidoujiaju.Fragment.BlankFragmentMei;
import com.first.zhaidoujiaju.Fragment.SelectMain;


public class MainActivity extends FragmentActivity implements View.OnClickListener {


    private RadioButton shou;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shou=((RadioButton) findViewById(R.id.shou_main));
        shou.setOnClickListener(this);
        ( findViewById(R.id.dan_main)).setOnClickListener(this);
        ( findViewById(R.id.mei_main)).setOnClickListener(this);
        ( findViewById(R.id.diy_main)).setOnClickListener(this);
        (findViewById(R.id.wo_main)).setOnClickListener(this);
        findViewById(R.id.select_main).setOnClickListener(this);
        findViewById(R.id.search_main).setOnClickListener(this);
         manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.layout_main, new BlankFragment());
        shou.setChecked(true);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction transaction=manager.beginTransaction();
       switch (view.getId()){
           case R.id.shou_main:
               transaction.replace(R.id.layout_main, new BlankFragment());
            break;
           case  R.id.dan_main:

             break;
           case R.id.mei_main:
               transaction.replace(R.id.layout_main, new BlankFragmentMei());
               break;
           case R.id.diy_main:
               break;
           case R.id.wo_main:
               break;
           case R.id.search_main:
               break;
           case R.id.select_main:
               Toast.makeText(MainActivity.this,"点击了select",Toast.LENGTH_LONG ).show();
               //transaction.add(R.id.linear_select, new SelectMain());
               break;

       }
        transaction.commit();
    }
}
