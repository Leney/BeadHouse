package com.shengyuan.beadhouse.gui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shengyuan.beadhouse.BuildConfig;
import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.base.BaseActivity;
import com.shengyuan.beadhouse.control.UserAccountManager;
import com.shengyuan.beadhouse.easyPermission.PermissionManager;
import com.shengyuan.beadhouse.glide.GlideLoader;
import com.shengyuan.beadhouse.gui.dialog.ChoicePhotoDialog;
import com.shengyuan.beadhouse.model.LoginBean;
import com.shengyuan.beadhouse.util.BitmapUtils;
import com.shengyuan.beadhouse.util.FileProviderUtils;

import java.io.File;

import rx.functions.Action1;

/**
 * 个人中心
 * Created by dell on 2017/11/12.
 */

public class PersonalCenterActivity extends BaseActivity implements View.OnClickListener, ChoicePhotoDialog.OnButtonClickListener {
    private LinearLayout headLay, accountLay, trueInfoLay, bindingPhoneLay, modifyPwdLay, careListLay, inviteControlLay;
    private ImageView icon;
    private TextView account, trueInfo, phone;
    private TextView careNum, inviteControlNum;

    private ChoicePhotoDialog choicePhotoDialog = null;

    /**
     * 封装照片的文件对象
     */
    private File tempFile;
    private String currentPicturePath;

    /**
     * 从相册界面跳转回来的标志
     */
    private final int PHOTO_REQUEST_GALLERY = 1;
    /**
     * 从相机界面跳转回来的标志
     */
    private final int PHOTO_REQUEST_CAREMA = 2;
    /**
     * 从裁剪界面跳转回来的标志
     */
    private final int PHOTO_REQUEST_CUT = 3;

    private final String[] writePermission = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};


    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_center;
    }

    @Override
    protected void initView() {
        baseTitle.setTitleName(getResources().getString(R.string.personal_center));
        headLay = (LinearLayout) findViewById(R.id.personal_head_icon_lay);
        accountLay = (LinearLayout) findViewById(R.id.personal_account_lay);
        trueInfoLay = (LinearLayout) findViewById(R.id.personal_modify_true_name_lay);
        bindingPhoneLay = (LinearLayout) findViewById(R.id.personal_modify_binding_phone_lay);
        modifyPwdLay = (LinearLayout) findViewById(R.id.personal_modify_pwd_lay);
        careListLay = (LinearLayout) findViewById(R.id.personal_care_old_man_lay);
        inviteControlLay = (LinearLayout) findViewById(R.id.personal_invite_control_man_lay);
        icon = (ImageView) findViewById(R.id.personal_center_icon);
        account = (TextView) findViewById(R.id.personal_center_account);
        trueInfo = (TextView) findViewById(R.id.personal_center_true_name);
        phone = (TextView) findViewById(R.id.personal_center_binding_phone);
        careNum = (TextView) findViewById(R.id.personal_care_old_man_num);
        inviteControlNum = (TextView) findViewById(R.id.personal_invite_control_man_num);

        headLay.setOnClickListener(this);
        accountLay.setOnClickListener(this);
        trueInfoLay.setOnClickListener(this);
        bindingPhoneLay.setOnClickListener(this);
        modifyPwdLay.setOnClickListener(this);
        careListLay.setOnClickListener(this);
        inviteControlLay.setOnClickListener(this);

        // 获取本地数据库中保存的个人信息
        getPersonalInfo();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_head_icon_lay:
                // 头像部分
                if (PermissionManager.checkPermission(this, writePermission)) {
                    showTakePhotoDialog();
                }
                break;
            case R.id.personal_account_lay:
                // 帐号部分
                break;
            case R.id.personal_modify_true_name_lay:
                // 真实信息部分
                TrueInfoActivity.startActivityForResult(PersonalCenterActivity.this);
                break;
            case R.id.personal_modify_binding_phone_lay:
                // 更改绑定手机部分
                BindPhoneActivity.startActivity(PersonalCenterActivity.this);
                break;
            case R.id.personal_modify_pwd_lay:
                // 更改密码部分
                ModifyPwdActivity.startActivity(PersonalCenterActivity.this, account.getText().toString().trim());
                break;
            case R.id.personal_care_old_man_lay:
                // 关注老人部分
                break;
            case R.id.personal_invite_control_man_lay:
                // 邀请监控人部分
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TrueInfoActivity.REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    // 完善用户信息成功
                    // 再次去获取当前用户的信息
                    getPersonalInfo();
                }
                break;
            case PHOTO_REQUEST_CAREMA://从相机拍照返回
                Log.i("llj", "从相机拍照返回!!!");
                if (hasSdcard()) {
                    currentPicturePath = tempFile.getAbsolutePath();
                    crop(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(this, "未找到存储卡，无法存储照片！", Toast.LENGTH_LONG).show();
                }
                break;
            case PHOTO_REQUEST_GALLERY:
                //从相册返回
                Log.i("llj", "从相册返回!!!");
                if (data != null) {
                    // 得到图片的全路径
                    Uri uri = data.getData();
                    crop(uri);
                }
                break;
            case PHOTO_REQUEST_CUT:
                //裁剪返回/storage/emulated/0/1514529877757.jpg
//                Log.i("llj","裁剪返回!!!,mImageFile.exist()---->>>"+mImageFile.exists());
//                Log.i("llj","mImageFile.path---->>>"+mImageFile.getAbsolutePath());
                if (resultCode == RESULT_OK) {
                    // 从剪切图片返回的数据
                    GlideLoader.loadNetWorkResource(PersonalCenterActivity.this, currentPicturePath, icon, false);
                }
                // 上传图片
                uploadImg();
                break;
            case PermissionManager.DEFAULT_SETTINGS_REQ_CODE:
                // 权限返回
                if (PermissionManager.checkPermission(PersonalCenterActivity.this, writePermission)) {
                    showTakePhotoDialog();
                }
                break;
        }
    }

    /**
     * 上传图片
     */
    private void uploadImg() {
        // TODO 上传图片
    }

    private void showTakePhotoDialog() {
        if (choicePhotoDialog == null) {
            choicePhotoDialog = new ChoicePhotoDialog(PersonalCenterActivity.this);
            choicePhotoDialog.setOnButtonClickListener(this);
        }
        choicePhotoDialog.show();
    }

    /**
     * 获取当前登陆的个人信息
     */
    private void getPersonalInfo() {
        UserAccountManager.getInstance().queryCurLoginAccount(new Action1<LoginBean>() {
            @Override
            public void call(LoginBean bean) {
                // 设置个人信息显示
                setInfoView(bean);
                showCenterView();
            }
        });
    }

    /**
     * 设置个人信息显示视图
     *
     * @param bean
     */
    private void setInfoView(LoginBean bean) {
        if (bean == null) return;
        GlideLoader.loadNetWorkResource(PersonalCenterActivity.this, bean.getUser().getPhoto(), icon, R.mipmap.personal_default_icon, true);
        account.setText(bean.getUser().getUsername());
        trueInfo.setText(bean.getUser().getName());
        phone.setText(bean.getUser().getUsername());
        // TODO 关注老人的数量、和邀请的监护人数量待定
    }

    @Override
    public void onDialogButtonClick(int type) {
        // 弹出框点击事件回调
        switch (type) {
            case ChoicePhotoDialog.TYPE_TAKE_CAMERA:
                // 相机
                takeCamera();
                break;
            case ChoicePhotoDialog.TYPE_TAKE_PHOTO:
                // 相册
                takePhoto();
                break;
        }
    }

    /**
     * 判断sdcard是否可用
     *
     * @return
     */
    private boolean hasSdcard() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    /**
     * 调起相机
     */
    private void takeCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (hasSdcard()) {
                    /*tempFile = new File(Environment.getExternalStorageDirectory(), "PHOTO_FILE_NAME");*/
            tempFile = BitmapUtils.getDiskFile(getApplicationContext(), BitmapUtils.getBitmapFileName());
            /**
             * 指定拍照存储路径
             * 7.0 及其以上使用FileProvider替换'file://'访问
             */
            if (Build.VERSION.SDK_INT >= 24) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", new File(tempFile.getAbsolutePath())));
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } else {
                // 从文件中创建uri
                Uri uri = Uri.fromFile(tempFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            }
        }
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
        startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
    }

    /**
     * 调起相册
     */
    private void takePhoto() {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    /**
     * 根据Uri裁剪图片
     *
     * @param uri
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= 24) {
            Log.i("llj", "裁剪前的图片路径：" + uri.getEncodedPath());
            Uri newUri = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", new File(uri.getEncodedPath()));
            intent.setDataAndType(newUri, "image/*");
            FileProviderUtils.grantUriPermission(this, intent, uri);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            intent.setDataAndType(uri, "image/*");
        }
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        // 图片格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        // 取消人脸识别
        intent.putExtra("noFaceDetection", true);
        File cropFile = BitmapUtils.getDiskFile(getApplicationContext(), BitmapUtils.getBitmapFileName());
        currentPicturePath = cropFile.getAbsolutePath();
        if (Build.VERSION.SDK_INT >= 24) {
            Log.i("llj", "裁剪后的存储的图片路径：" + currentPicturePath);
            Uri saveFileUri = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", cropFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, saveFileUri);
            FileProviderUtils.grantUriPermission(this, intent, saveFileUri);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            //设置裁剪后文件保存路径
            Uri cropUri = Uri.fromFile(cropFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);
        }
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, PersonalCenterActivity.class));
    }
}
