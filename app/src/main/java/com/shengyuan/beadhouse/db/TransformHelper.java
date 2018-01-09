package com.shengyuan.beadhouse.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.shengyuan.beadhouse.model.LoginBean;

/**
 * ContentValues和实体类互相转换的工具类
 * Created by llj on 2017/12/27.
 */

public class TransformHelper {
    /**
     * LoginBean实体转换成ContentValues
     *
     * @param bean
     * @return
     */
    public static ContentValues getLoginBeanContentValues(LoginBean bean) {
        if (bean == null) return null;
        ContentValues values = new ContentValues();
        values.put(DBColumns.COLUMNS_ID_NUMBER, bean.getUser().getID_number());
        values.put(DBColumns.COLUMNS_DATE_TIME, bean.getUser().getDatetime());
        values.put(DBColumns.COLUMNS_USER_ACCOUNT, bean.getUser().getUsername());
        values.put(DBColumns.COLUMNS_NAME, bean.getUser().getName());
        values.put(DBColumns.COLUMNS_PHOTO, bean.getUser().getPhoto());
        values.put(DBColumns.COLUMNS_SEX, bean.getUser().getSex());
        values.put(DBColumns.COLUMNS_USER_TOKEN, bean.getToken());
        values.put(DBColumns.COLUMNS_COMPLETE, bean.getComplete());
        values.put(DBColumns.COLUMNS_FOCUS_COUNT, bean.getFocus_count());
        values.put(DBColumns.COLUMNS_INVITE_COUNT, bean.getInvite_count());
        return values;
    }

    /**
     * Cursor转换成LoginBean实体
     *
     * @param cursor
     * @return
     */
    public static LoginBean getLoginBean(Cursor cursor) {
        if (cursor == null) return null;
        LoginBean loginBean = new LoginBean();
        LoginBean.UserBean userBean = new LoginBean.UserBean();
        userBean.setID_number(cursor.getString(cursor.getColumnIndex(DBColumns.COLUMNS_ID_NUMBER)));
        userBean.setDatetime(cursor.getString(cursor.getColumnIndex(DBColumns.COLUMNS_DATE_TIME)));
        userBean.setUsername(cursor.getString(cursor.getColumnIndex(DBColumns.COLUMNS_USER_ACCOUNT)));
        userBean.setName(cursor.getString(cursor.getColumnIndex(DBColumns.COLUMNS_NAME)));
        userBean.setPhoto(cursor.getString(cursor.getColumnIndex(DBColumns.COLUMNS_PHOTO)));
        userBean.setSex(cursor.getString(cursor.getColumnIndex(DBColumns.COLUMNS_SEX)));
        loginBean.setToken(cursor.getString(cursor.getColumnIndex(DBColumns.COLUMNS_USER_TOKEN)));
        loginBean.setComplete(cursor.getString(cursor.getColumnIndex(DBColumns.COLUMNS_COMPLETE)));
        loginBean.setFocus_count(cursor.getInt(cursor.getColumnIndex(DBColumns.COLUMNS_FOCUS_COUNT)));
        loginBean.setInvite_count(cursor.getInt(cursor.getColumnIndex(DBColumns.COLUMNS_INVITE_COUNT)));
        loginBean.setUser(userBean);
        return loginBean;
    }
}
