package com.first.zhaidoujiaju;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.text.TextPaint;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.factory.BitmapFactory;

/**
 * Created by Administrator on 15-10-6.
 */
public class BitmapHelper {
    private  static BitmapUtils utils;
    public static void init(Context context){
        utils=new BitmapUtils(context);
        BitmapDisplayConfig config=new BitmapDisplayConfig();
        config.setBitmapFactory(new Factory());
        utils.configDefaultDisplayConfig(config);
        utils.configDefaultLoadFailedImage(R.mipmap.defaultimage);
        //utils.configDefaultLoadingImage(R.mipmap.ic_launcher);
        utils.clearCache();

    }
    public static  BitmapUtils getUtils(){
        return utils;
    }
    public  static class Factory implements BitmapFactory {
        public Factory() {
        }

        @Override
        public BitmapFactory cloneNew() {
            return new Factory();
        }

        @Override
        public Bitmap createBitmap(Bitmap bitmap) {
            Bitmap result=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),Bitmap.Config.ARGB_8888);
            Canvas canvas=new Canvas(result);
            //添加渐变
            Paint paint=new Paint();
            LinearGradient lg1 = new LinearGradient(0,bitmap.getHeight()-200,0,bitmap.getHeight()-10, Color.argb(0,255,255,255),Color.BLACK, Shader.TileMode.CLAMP);
            paint.setShader(lg1);
            paint.setAlpha(200);
            canvas.drawBitmap(bitmap, 0, 0, null);
            canvas.drawPaint(paint);


            //左上角添加图片
            Paint paint1=new Paint();
            paint1.setColor(Color.RED);
            Path path=new Path();
            path.moveTo(20, 0);
            path.lineTo(60, 0);
            path.lineTo(0, 60);
            path.lineTo(0, 20);
            path.lineTo(20, 0);
            canvas.drawPath(path, paint1);

            Paint paint3=new Paint();
            paint3.setColor(Color.GRAY);
            Path path3=new Path();
            path3.moveTo(0, bitmap.getHeight());
            path3.lineTo(0, bitmap.getHeight() - 6);
            path3.lineTo(6, bitmap.getHeight());
            path3.lineTo(0, bitmap.getHeight());
            canvas.drawPath(path3, paint3);

            Paint paint4=new Paint();
            paint4.setColor(Color.GRAY);
            Path path4=new Path();
            path4.moveTo(bitmap.getWidth(),bitmap.getHeight());
            path4.lineTo(bitmap.getWidth(), bitmap.getHeight() - 6);
            path4.lineTo(bitmap.getWidth() - 6, bitmap.getHeight());
            path4.lineTo(bitmap.getWidth(),bitmap.getHeight());
            canvas.drawPath(path4,paint4);

            bitmap.recycle();
            return result;
        }
    }
}
