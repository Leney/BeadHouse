package com.shengyuan.beadhouse.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.shengyuan.beadhouse.R;


/**
 * Created by ${xingen} on 2017/7/5.
 * <p>
 * 图片异步加载的操作类
 */

public class GlideLoader {
    /**
     * 加载本地Resource图片
     */
    public static void loadLocalResource(Context context, int resourceId, ImageView imageView) {
        loadLocalResource(context, resourceId, imageView, false);
    }

    /**
     * 加载本地Resource,图片
     *
     * @param context
     * @param resourceId
     * @param imageView
     * @param isCircle   是否为圆角
     */
    public static void loadLocalResource(Context context, int resourceId, ImageView imageView, boolean isCircle) {
        GlideRequest<Bitmap> glideRequest = GlideApp.with(context).asBitmap();
        if (isCircle) {//进行圆角转换
            glideRequest.circleCrop().transform(new CircleTransform(context));
        }else{
            glideRequest.centerCrop();
        }
        glideRequest.load(resourceId).into(imageView);
    }

    /**
     * 加载本地Resource,生成指定的圆角的图片
     *
     * @param context
     * @param resourceId
     * @param imageView
     * @param cornerRadius 圆角度数
     */
    public static void loadLocalResource(Context context, int resourceId, ImageView imageView, float cornerRadius) {
        GlideApp.with(context).asBitmap().load(resourceId).into(new CircularBitmapImageViewTarget(context, imageView, cornerRadius));
    }

    /**
     * 加载本地Resource,生成指定的圆角的图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param cornerRadius 圆角度数
     */
    public static void loadNetWorkResource(Context context, String url, ImageView imageView, float cornerRadius,int defaultRes,int errorRes) {
        GlideApp.with(context).asBitmap().load(url).placeholder(defaultRes).error(errorRes).into(new CircularBitmapImageViewTarget(context, imageView, cornerRadius));
    }

    /**
     * 加载网络图片
     *
     * @param context
     * @param imageUrl
     * @param imageView
     */
    public static void loadNetWorkResource(Context context, String imageUrl, ImageView imageView) {
        loadNetWorkResource(context, imageUrl, imageView, false);
    }

    /**
     * 加载网络图片  , 圆角显示
     *
     * @param context
     * @param imageUrl
     * @param imageView
     * @param isCircle
     */
    public static void loadNetWorkResource(Context context, String imageUrl, ImageView imageView, boolean isCircle) {
        int defaultImageResource = R.mipmap.ic_launcher;
        loadNetWorkResource(context, imageUrl, imageView, defaultImageResource, isCircle);
    }

    public static  void loadGifResource(Context context, String imageUrl, ImageView imageView){
        int defaultImageResource = R.mipmap.ic_launcher;
        GlideApp.with(context).asGif().load(imageUrl).error(defaultImageResource).placeholder(defaultImageResource).into(imageView);
    }

    /**
     * 加载网络图片  , 圆角显示
     *
     * @param context
     * @param imageUrl
     * @param imageView
     * @param isCircle
     */
    public static void loadNetWorkResource(Context context, String imageUrl, ImageView imageView, int defaultImageResource, boolean isCircle) {

        loadNetWorkResource(context, imageUrl, imageView, defaultImageResource, defaultImageResource, isCircle);
    }

    /**
     * 加载网络图片  , 圆角显示
     *
     * @param context
     * @param imageUrl
     * @param imageView
     * @param isCircle
     */
    public static void loadNetWorkResource(Context context, String imageUrl, ImageView imageView, int defaultImageResource, int errorResourceId, boolean isCircle) {

        loadNetWorkResource(context, imageUrl, imageView, defaultImageResource, errorResourceId, defaultImageResource, isCircle);
    }

    /**
     * 加载网络图片
     *
     * @param context
     * @param imageUrl
     * @param imageView
     * @param nullResourceId
     * @param placeResourceId
     * @param errorResourceId
     * @param isCircle
     */
    public static void loadNetWorkResource(Context context, String imageUrl, ImageView imageView, int nullResourceId, int placeResourceId, int errorResourceId, boolean isCircle) {
        GlideRequest<Bitmap> glideRequest = GlideApp.with(context).asBitmap();
        if (isCircle) {//进行圆角转换
            glideRequest.circleCrop().transform(new CircleTransform(context));
        }else{
            glideRequest.centerCrop();
        }
        glideRequest.load(imageUrl).error(errorResourceId)//异常时候显示的图片
                .placeholder(placeResourceId)//加载成功前显示的图片
                .fallback(nullResourceId)//url为空的时候，显示的图片
                .into(imageView);
    }

    /**
     *  加载全部圆形的Bitmap。
     * @param context
     * @param imageUrl
     * @param imageView
     * @param placeResourceId
     * @param errorResourceId
     */
    public static void loadCircleNetWorkBitmap(Context context, String imageUrl, ImageView imageView,int placeResourceId, int errorResourceId){
        loadCircleNetWorkBitmap(context,imageUrl,imageView,placeResourceId,placeResourceId,errorResourceId);
    }
    public static void loadCircleNetWorkBitmap(Context context, String imageUrl, ImageView imageView, int nullResourceId, int placeResourceId, int errorResourceId){
        GlideRequest<Bitmap> glideRequest = GlideApp.with(context).asBitmap();
        glideRequest.load(imageUrl).error(errorResourceId)//异常时候显示的图片
                .placeholder(placeResourceId)//加载成功前显示的图片
                .fallback(nullResourceId)//url为空的时候，显示的图片
                .into(new CircleBitmapTarget(imageView));//在RequestBuilder<Bitmap> 中使用自定义的ImageViewTarget
    }

}
