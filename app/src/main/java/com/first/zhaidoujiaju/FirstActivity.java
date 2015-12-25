package com.first.zhaidoujiaju;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.first.zhaidoujiaju.Utils.PrefUtils;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {

    private static final int[] imageIds = new int[]{R.mipmap.img001, R.mipmap.img002, R.mipmap.img003};
    private ViewPager pager;
    private ArrayList<ImageView> imagesList;
    private LinearLayout pointGroup;
    private View redPoint;
    private Button start;
    private int mPointWidth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        pager = ((ViewPager) findViewById(R.id.guide_pager));
        pointGroup = ((LinearLayout) findViewById(R.id.ll_point_group));
        redPoint = findViewById(R.id.view_red_point);
        start = ((Button) findViewById(R.id.btn_start));
        initViews();
        pager.setAdapter(new GuideAdapter());
        pager.setOnPageChangeListener(new GuidePagerListener());

        boolean userGuide = PrefUtils.getBoolean(this, "is_user_guide_showed",
                false);
        if(userGuide){
            startActivity(new Intent(FirstActivity.this,MainActivity.class));
        }
        start.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                PrefUtils.setBoolean(FirstActivity.this,
                        "is_user_guide_showed", true);
                startActivity(new Intent(FirstActivity.this,MainActivity.class));
                finish();
            }
        });
    }
    private void initViews() {
        imagesList = new ArrayList<>();
        for (int i = 0; i < imageIds.length; i++) {
            ImageView image = new ImageView(this);
            image.setBackgroundResource(imageIds[i]);
            imagesList.add(image);
        }

        //初始化小圆点
        for (int i = 0; i < imageIds.length; i++) {
            View point = new View(this);
            point.setBackgroundResource(R.drawable.shape_guid_pointgray);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
            if (i > 0) {
                params.leftMargin = 40;//设置圆点间隔

            }
            point.setLayoutParams(params);//设置圆点大小
            pointGroup.addView(point);
        }
        // 获取视图树, 对layout结束事件进行监听
        pointGroup.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    // 当layout执行结束后回调此方法
                    @Override
                    public void onGlobalLayout() {
                        System.out.println("layout 结束");
                        pointGroup.getViewTreeObserver()
                                .removeGlobalOnLayoutListener(this);
                        mPointWidth = pointGroup.getChildAt(1).getLeft()
                                - pointGroup.getChildAt(0).getLeft();
                        System.out.println("圆点距离:" + mPointWidth);
                    }
                });
    }
    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageIds.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imagesList.get(position));
            return imagesList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

    }
    class GuidePagerListener implements ViewPager.OnPageChangeListener{

        // 滑动事件
        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
            int len = (int) (mPointWidth * positionOffset) + position
                    * mPointWidth;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) redPoint
                    .getLayoutParams();// 获取当前红点的布局参数
            params.leftMargin = len;// 设置左边距
            redPoint.setLayoutParams(params);// 重新给小红点设置布局参数
        }

        // 某个页面被选中
        @Override
        public void onPageSelected(int position) {
            if (position == imageIds.length - 1) {// 最后一个页面
                start.setVisibility(View.VISIBLE);// 显示开始体验的按钮
            } else {
                start.setVisibility(View.INVISIBLE);
            }
        }

        public void onPageScrollStateChanged(int position) {

        }
    }


}
