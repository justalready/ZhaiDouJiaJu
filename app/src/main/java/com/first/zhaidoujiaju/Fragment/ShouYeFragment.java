package com.first.zhaidoujiaju.Fragment;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.android.volley.Response;
import com.first.zhaidoujiaju.Adapter.EntityLuoAdapter;
import com.first.zhaidoujiaju.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class ShouYeFragment extends Fragment{

    private static int count=0;
    public  ViewPager pager;
    private  int images[]={R.mipmap.aaaa,R.mipmap.bbbb,R.mipmap.cccc,R.mipmap.dddd,R.mipmap.eeee};
    private List<ImageView> list;
    private  RadioButton[] buttons;
    private EntityLuoAdapter adapterimage1;

    private   Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int count=msg.arg1;
            for (int i = 0; i < images.length; i++) {
                buttons[i].setChecked(false);
            }
            buttons[count].setChecked(true);
            pager.setCurrentItem(count);
        };
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shoueye, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
   //     ((RadioButton) view.findViewById(R.id.shou_main)).setChecked(true);
        pager = ((ViewPager) view.findViewById(R.id.pager));

        list=new ArrayList<ImageView>();
        for (int i = 0; i < images.length; i++) {
            ImageView imageView=new ImageView(getActivity());
            imageView.setImageResource(images[i]);
            list.add(imageView);
        }


        new Thread(){
            @Override
            public void run() {
                try {
                    while(true) {
                        Thread.sleep(1000);
                        Message msg = Message.obtain();
                        msg.arg1 = count;
                        handler.sendMessage(msg);
                        count++;
                        count = count % 3;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();

        //初始化指示圆点
        RadioGroup group = (RadioGroup) view.findViewById(R.id.group);
        buttons=new RadioButton[images.length];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i]= ((RadioButton) group.getChildAt(i));
            buttons[i].setChecked(false);
            buttons[i].setTag(i);
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pager.setCurrentItem((Integer) view.getTag());
                    count= (int) view.getTag();
                }
            });
        }
        buttons[0].setChecked(true);

        pager.setAdapter(new MyPagerAdapter());
        pager.setOffscreenPageLimit(2);
        pager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < images.length; i++) {
                    buttons[i].setChecked(false);
                }
                buttons[position].setChecked(true);
                count = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

       // adapterimage1=new EntityLuoAdapter();


    }
    class  MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageshou=list.get(position);
            imageshou.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.addView(imageshou);
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }
    }

}
