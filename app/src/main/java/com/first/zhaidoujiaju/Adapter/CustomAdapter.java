package com.first.zhaidoujiaju.Adapter;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.annotation.JSONField;
import com.first.zhaidoujiaju.BitmapHelper;
import com.first.zhaidoujiaju.EntityLuo.EntieyShou;
import com.first.zhaidoujiaju.Fragment.ListViewXiangQing;
import com.first.zhaidoujiaju.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 15-10-5.
 */
public class CustomAdapter extends BaseAdapter {
    private Context context;
    private List<EntieyShou> list;
    private SendId sendId;

    public CustomAdapter(Context context, List<EntieyShou> list) {
        this.context = context;
        this.list = list;
    }

//    public CustomAdapter(SendId sendId) {
//        this.sendId = sendId;
//    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void clear(){
        list.clear();
        notifyDataSetChanged();
    }
    public void addAll(Collection<? extends EntieyShou> collection){
        list.addAll(collection);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_shou, viewGroup, false);
            view.setTag(new ViewHolder(view));
        }
        ViewHolder holder= (ViewHolder) view.getTag();
        EntieyShou entieyShou=list.get(i);
        String id=entieyShou.getId();
        Log.d("CustomAdapter", id);
        Log.d("CustomAdapter", entieyShou.getTitle());

//        sendId.send(id);
        holder.title.setText(entieyShou.getTitle());
        holder.num.setText(entieyShou.getReviews());
        if(TextUtils.isEmpty(entieyShou.getImg_url())){
            holder.image.setVisibility(View.GONE);
        }else{
            holder.image.setVisibility(View.VISIBLE);
            BitmapHelper.getUtils().display(holder.image, entieyShou.getImg_url());
        }

        return view;
    }


    private static class ViewHolder{
        @ViewInject(R.id.text2_item)
        private TextView title;
        @ViewInject(R.id.iamge_item)
        private  ImageView image;
        @ViewInject(R.id.num_item)
        private  TextView num;
        public ViewHolder(View itemView){
            ViewUtils.inject(this,itemView);
        }

    }

    public interface SendId{

         void send(String s);
    }
}
