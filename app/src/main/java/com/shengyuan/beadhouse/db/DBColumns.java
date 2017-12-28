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

    /**
     * 用户信息表
     */
    public static final String TABLE_USER_INFO = "table_user_info";
    /**
     * 身份证id
     */
    public static final String COLUMNS_ID_NUMBER = "id_number";
    /**
     * 创建时间
     */
    public static final String COLUMNS_DATE_TIME = "date_time";
    /**
     * 用户账号名称(手机号)
     */
    public static final String COLUMNS_USER_ACCOUNT = "account";
    /**
     * 用户token
     */
    public static final String COLUMNS_USER_TOKEN = "token";
    /**
     * 用户名称
     */
    public static final String COLUMNS_NAME = "name";
    /**
     * 用户头像
     */
    public static final String COLUMNS_PHOTO = "photo";
    /**
     * 用户性别
     */
    public static final String COLUMNS_SEX = "sex";
    /**
     * 是否完善了资料
     */
    public static final String COLUMNS_COMPLETE = "complete";
}
