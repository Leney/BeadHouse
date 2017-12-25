package com.shengyuan.beadhouse.db;

/**
 * sql语句类
 * Created by llj on 2017/12/22.
 */

public class SqlHelper {
    /**
     * 创建用户信息表的sql语句
     */
    public static final String CREATE_TABLE_USER_INFO = "create table if not exists "
            + DBColumns.TABLE_USER_INFO
            + " ("
            + DBColumns._ID
            + " integer primary key autoincrement, "
            + DBColumns.COLUMNS_USER_ID
            + " integer, "
            + DBColumns.COLUMNS_USER_PHONE
            + " text, "
            + DBColumns.COLUMNS_USER_TOKEN
            + " text, "
            + DBColumns.COLUMNS_USER_PHONE
            + " text, "
            + DBColumns.COLUMNS_NAME
            + " text, "
            + DBColumns.COLUMNS_ICON
            + " text,"
            + DBColumns.COLUMNS_SEX
            + " bit);";
}
