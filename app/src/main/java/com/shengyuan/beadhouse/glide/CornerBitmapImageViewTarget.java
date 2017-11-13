package com.shengyuan.beadhouse.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by ${xingen} on 2017/7/5.
 *
 * 根据指定的角的圆角度数
 */

public class CornerBitmapImageViewTarget extends BitmapImageViewTarget {
    private Context context;
    private ImageView imageView;
    private float cornerRadius;
    public CornerBitmapImageViewTarget(Context context, ImageView view, float cornerRadius) {
        super(view);
        this.context=context;
        this.cornerRadius=cornerRadius;
        this.imageView=view;
    }
    /**
     * 重写 setResource（），生成圆角的图片
     * @param resource
     */
    @Override
    protected void setResource(Bitmap resource) {
        RoundedBitmapDrawable bitmapDrawable= RoundedBitmapDrawableFactory.create(this.context.getResources(),resource);
        /**
         *   设置图片的shape为圆形.
         */
        bitmapDrawable.setCornerRadius(cornerRadius);
        this.imageView.setImageDrawable(bitmapDrawable);
    }
}
