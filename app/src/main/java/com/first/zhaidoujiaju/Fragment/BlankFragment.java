package com.first.zhaidoujiaju.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.first.zhaidoujiaju.Adapter.CustomAdapter;
import com.first.zhaidoujiaju.DbHelper;
import com.first.zhaidoujiaju.EntityLuo.EntieyShou;
import com.first.zhaidoujiaju.OnRefresh;
import com.first.zhaidoujiaju.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    public ListView listView;
    public SwipeRefreshLayout swipe;
    private static int count=0;
    public  ViewPager pager;
    private  int images[]={R.mipmap.aaaa,R.mipmap.bbbb,R.mipmap.cccc,R.mipmap.dddd,R.mipmap.eeee};
    private List<ImageView> list;
    private  RadioButton[] buttons;
    private boolean isRunning=true;
    private CustomAdapter adapter;
    private boolean sign=false;
    private  static int index=1;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            count=count%5;
            pager.setCurrentItem(count);
            count++;
            if(isRunning){
                handler.sendEmptyMessageDelayed(0,2000);
            }
        };
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning=false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ViewUtils.inject(this,view);
        listView = (ListView) view.findViewById(R.id.listView_shou);
        listView.addHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.fragment_shoueye,null,true));

        List<EntieyShou> all=null;
        try {
            all= DbHelper.getUtils().findAll(Selector.from(EntieyShou.class).orderBy("id",true).limit(20));
        } catch (DbException e) {
            e.printStackTrace();
        }
        if(all==null){
            all=new ArrayList<EntieyShou>();
        }
        swipe = ((SwipeRefreshLayout) view.findViewById(R.id.swipe_shou));
        adapter=new CustomAdapter(getActivity(), all);
        listView.setAdapter(adapter);

        pager = ((ViewPager) view.findViewById(R.id.pager));
        list=new ArrayList<ImageView>();
        for (int i = 0; i < images.length; i++) {
            ImageView imageView=new ImageView(getActivity());
            imageView.setImageResource(images[i]);
            list.add(imageView);
        }
        handler.sendEmptyMessageDelayed(0,2000);
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
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.fragment_image1, new BlankFragment2());
        transaction.replace(R.id.fragment_image2, new FragmentNv());
        transaction.commit();

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (sign && (i == AbsListView.OnScrollListener.SCROLL_STATE_IDLE)) {
                    index++;
                    HttpUtils utils = new HttpUtils(1000, "key:values");
                    utils.send(HttpRequest.HttpMethod.GET, "http://www.zhaidou.com/article/api/articles?page=" + index + "&catetory_id", new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            JSONArray array = JSON.parseObject(responseInfo.result).getJSONArray("articles");
                            EntieyShou entieyShou;
                            List<EntieyShou> list = new ArrayList<EntieyShou>();
                            for (int i = 0; i < array.size(); i++) {
                                entieyShou = array.getObject(i, EntieyShou.class);
                                list.add(entieyShou);
                            }
                            adapter.addAll(list);
                            try {
                                DbHelper.getUtils().saveOrUpdateAll(list);
                            } catch (DbException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            e.printStackTrace();

                        }
                    });


                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                sign = (i + i1 == i2);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent=new Intent(getActivity(),ListViewXiangQing.class);
                        startActivity(intent);
            }
        });

    }



    @OnRefresh(R.id.swipe_shou)
    public void onRefresh(){
        adapter.clear();
        HttpUtils utils=new HttpUtils(1000,"key:values");
        utils.send(HttpRequest.HttpMethod.GET,"http://www.zhaidou.com/article/api/articles?page="+index+"&catetory_id", new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                JSONArray array= JSON.parseObject(responseInfo.result).getJSONArray("articles");
                EntieyShou entieyShou;
                List<EntieyShou> list=new ArrayList<EntieyShou>();
                for (int i = 0; i < array.size(); i++) {
                    entieyShou=array.getObject(i,EntieyShou.class);
                    list.add(entieyShou);
                }
                adapter.addAll(list);
                try {
                    DbHelper.getUtils().saveOrUpdateAll(list);
                } catch (DbException e) {
                    e.printStackTrace();
                }
                swipe.setRefreshing(false);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                 e.printStackTrace();
                swipe.setRefreshing(false);
            }
        });

    }

    class  MyPagerAdapter extends PagerAdapter {

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
