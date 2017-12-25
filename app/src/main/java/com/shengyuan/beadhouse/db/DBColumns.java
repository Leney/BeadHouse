package com.shengyuan.beadhouse.db;

import android.provider.BaseColumns;

/**
 * Created by llj on 2017/12/22.
 */

public class DBColumns implements BaseColumns {
    /**
     * 数据库信息
     */
    public static final String SQLITE_NAME = "BeadHouse.db";
    public static final int SQLITE_VERSON = 1;

    /** 用户信息表*/
    public static final String TABLE_USER_INFO = "table_user_info";
    /** 用户id*/
    public static final String COLUMNS_USER_ID = "user_id";
    /** 用户手机号*/
    public static final String COLUMNS_USER_PHONE = "phone";
    /** 用户token*/
    public static final String COLUMNS_USER_TOKEN = "token";
    /** 用户名称*/
    public static final String COLUMNS_NAME = "name";
    /** 用户头像*/
    public static final String COLUMNS_ICON = "icon";
    /** 用户性别，0=男，1=女*/
    public static final String COLUMNS_SEX="sex";
}
