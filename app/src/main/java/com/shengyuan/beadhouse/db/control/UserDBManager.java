package com.shengyuan.beadhouse.db.control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.shengyuan.beadhouse.BHApplication;
import com.shengyuan.beadhouse.db.DBColumns;
import com.shengyuan.beadhouse.db.DBHelper;
import com.shengyuan.beadhouse.db.TransformHelper;
import com.shengyuan.beadhouse.model.LoginBean;

/**
 * 用户信息数据库管理类
 * Created by llj on 2017/12/27.
 */

public class UserDBManager {
    private static UserDBManager instance = null;

    private DBHelper dbHelper = null;

    private UserDBManager(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public static UserDBManager getInstance() {
        if (instance == null) {
            synchronized (UserDBManager.class) {
                if (instance == null) {
                    instance = new UserDBManager(BHApplication.getContext());
                }
            }
        }
        return instance;
    }

    /**
     * 插入一条用户信息数据
     *
     * @param bean
     */
    public void insert(LoginBean bean) {
        if (bean == null) return;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            ContentValues values = TransformHelper.getLoginBeanContentValues(bean);
            db.insert(DBColumns.TABLE_USER_INFO, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    /**
     * 插入一条新的账户数据到数据库
     *
     * @param bean
     */
    public void insertNewUser(LoginBean bean) {
        if (bean == null) return;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(
                    DBColumns.TABLE_USER_INFO
                    , null
                    , null
                    , null
                    , null, null, null);
            LoginBean curLoginBean = null;
            if (cursor != null && cursor.moveToFirst()) {
                curLoginBean = TransformHelper.getLoginBean(cursor);
            }
            if (curLoginBean != null) {
                // 数据库中之前有登陆账号信息
                // 删除之前的数据
                db.delete(DBColumns.TABLE_USER_INFO, null, null);
            }

            // 插入一条新的登陆用户信息
            ContentValues values = TransformHelper.getLoginBeanContentValues(bean);
            db.insert(DBColumns.TABLE_USER_INFO, null, values);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

    /**
     * 更新一条数据
     *
     * @param bean
     */
    public void update(LoginBean bean) {
        if (bean == null) return;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            ContentValues values = TransformHelper.getLoginBeanContentValues(bean);
            db.update(
                    DBColumns.TABLE_USER_INFO, values
                    , DBColumns.COLUMNS_USER_ACCOUNT + "=?"
                    , new String[]{bean.getUser().getUsername()});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    /**
     * 查询出当前登陆用户的信息
     */
    public LoginBean query() {
        Log.i("llj", "dbHelper == null ---->>>" + (dbHelper == null));
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(
                    DBColumns.TABLE_USER_INFO
                    , null
                    , null
                    , null
                    , null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                return TransformHelper.getLoginBean(cursor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return null;
    }

    /**
     * 完善个人资料
     */
    public LoginBean perfectUserInfo(String name, String sex, String cardId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(
                    DBColumns.TABLE_USER_INFO
                    , null
                    , null
                    , null
                    , null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                LoginBean loginBean = TransformHelper.getLoginBean(cursor);
                loginBean.setComplete("yes");
                loginBean.getUser().setName(name);
                loginBean.getUser().setSex(sex);
                loginBean.getUser().setID_number(cardId);

                ContentValues values = TransformHelper.getLoginBeanContentValues(loginBean);
                db.update(
                        DBColumns.TABLE_USER_INFO, values
                        , DBColumns.COLUMNS_USER_ACCOUNT + "=?"
                        , new String[]{loginBean.getUser().getUsername()});
                return loginBean;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return null;
    }


    /**
     * 删除当前登陆用户信息
     */
    public void delete() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.delete(DBColumns.TABLE_USER_INFO, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
}
