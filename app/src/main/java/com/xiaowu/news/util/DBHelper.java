package com.xiaowu.news.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.xiaowu.news.model.News;

import java.util.List;

/**
 * Created by caishijian on 16-8-26.
 */
public class DBHelper {

    private static DBHelper dbHelper;
    private SQLiteDatabase db;

    private DBHelper(Context context) {
        DBOpenHelper dbOpenHelper = new DBOpenHelper(context);
        db = dbOpenHelper.getWritableDatabase();
    }

    /**
     * 获取 DBHelperNews的实例
     * @param context
     * @return
     */
    public static DBHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context);
        }
        return dbHelper;
    }

    public void addNews(News news) {
        if (news != null) {
            ContentValues values = new ContentValues();
            values.put("nid", news.getNid());
            values.put("cid", news.getCid());
            values.put("title", news.getTitle());
            values.put("digest", news.getDigest());
            values.put("body", news.getBody());
            values.put("source", news.getSource());
            values.put("commentCount", news.getCommentCount());
            values.put("ptime", news.getPtime());
            values.put("imgSrc", news.getImgSrc());
            values.put("deleted", news.getDeleted());
            db.insert("tb_news", null, values);
        } else {
            System.out.println("news is null!");
        }
    }


    public void deleteRecordById(int id) {

    }


    public void deleteRecord(News obj) {

    }


    public void updateRecord(int id) {

    }


    public List<News> queryRecord(int id) {
        return null;
    }
}
