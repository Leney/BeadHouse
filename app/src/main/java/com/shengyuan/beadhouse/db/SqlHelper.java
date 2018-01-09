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
            + DBColumns.COLUMNS_ID_NUMBER
            + " text, "
            + DBColumns.COLUMNS_DATE_TIME
            + " text, "
            + DBColumns.COLUMNS_USER_ACCOUNT
            + " text, "
            + DBColumns.COLUMNS_USER_TOKEN
            + " text, "
            + DBColumns.COLUMNS_NAME
            + " text, "
            + DBColumns.COLUMNS_PHOTO
            + " text,"
            + DBColumns.COLUMNS_COMPLETE
            + " text,"
            + DBColumns.COLUMNS_FOCUS_COUNT
            + " integer,"
            + DBColumns.COLUMNS_INVITE_COUNT
            + " integer,"
            + DBColumns.COLUMNS_SEX
            + " text);";
}
