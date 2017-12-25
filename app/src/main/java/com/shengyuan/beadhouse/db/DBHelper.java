package com.shengyuan.beadhouse.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by ${xingen} on 2017/7/7.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, DBColumns.SQLITE_NAME, null, DBColumns.SQLITE_VERSON);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // 创建用户信息表
        sqLiteDatabase.execSQL(SqlHelper.CREATE_TABLE_USER_INFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }


}
