package com.first.zhaidoujiaju.Adapter;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;

/**
 * Created by Administrator on 15-10-4.
 */
public class EntityLuoAdapter extends BaseAdapter implements ImageLoader.ImageCache,ImageRequest.Transformation {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public Bitmap getBitmap(String url) {
        return null;
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {

    }

    @Override
    public Bitmap transform(Bitmap source, int maxWidth, int maxHeight) {
        return null;
    }

    @Override
    public String key() {
        return null;
    }
}
