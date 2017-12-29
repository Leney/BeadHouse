package com.shengyuan.beadhouse.gui.dialog;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;

/**
 * 上传照片的选择dialog
 * Created by dell on 2017/11/25.
 */

public class ChoicePhotoDialog extends BottomDialog {

    /**
     * 相机
     */
    public static final int TYPE_TAKE_CAMERA = 1;
    /**
     * 相册
     */
    public static final int TYPE_TAKE_PHOTO = 2;


    private TextView takePicture, photoAlbum, cancelBtn;

    private OnButtonClickListener mListener;

    public ChoicePhotoDialog(Activity activity) {
        super(activity, R.layout.dialog_choice_picture);
        initView();
    }

    private void initView() {
        takePicture = findViewById(R.id.dialog_choice_take_photo);
        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 拍照
                dismiss();
                if(mListener != null){
                    mListener.onDialogButtonClick(TYPE_TAKE_CAMERA);
                }
            }
        });

        photoAlbum = findViewById(R.id.dialog_choice_album);
        photoAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 相册
                dismiss();
                if(mListener != null){
                    mListener.onDialogButtonClick(TYPE_TAKE_PHOTO);
                }
            }
        });

        cancelBtn = findViewById(R.id.dialog_choice_cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取消
                dismiss();
            }
        });
    }

    public void setOnButtonClickListener(OnButtonClickListener listener){
        this.mListener = listener;
    }

    public interface OnButtonClickListener{
        /** 点击按钮类型 0=拍照，1=相册*/
        void onDialogButtonClick(int type);
    }
}
