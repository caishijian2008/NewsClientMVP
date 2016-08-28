package com.xiaowu.news.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by caishijian on 16-8-2.
 */
public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "db_news.db";

    /*public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }*/
    private static final String CREATE_NEWS = "create table if not exists tb_news(" +
            "nid integer not null primary key autoincrement, " +
            "cid int not null, " +
            "title varchar(255), " +
            "digest varchar(255), " +
            "body text, " +
            "source varchar(20), " +
            "commentCount int default '0', " +
            "ptime varchar(20), " +
            "imgSrc text, " +
            "deleted int default '0')";

    private static final String CREATE_COMMENT = "create table if not exists t_comment(" +
            "cid integer not null primary key autoincrement, " +
            "nid int not null, " +
            "ptime varchar(20), " +
            "region varchar(255), " +
            "content text, " +
            "supportcount int default '0', " +
            "opposecount int default '0', " +
            "deleted int default '0')";

    private static final String CREATE_CATEGORY = "create table if not exists t_category(" +
            "cid integer not null primary key autoincrement, " +
            "title varchar(255), " +
            "sequnce int default '0', " +
            "deleted int default '0')";

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_NEWS);
        sqLiteDatabase.execSQL(CREATE_COMMENT);
        sqLiteDatabase.execSQL(CREATE_CATEGORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
