package com.xiaowu.news.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xiaowu.news.model.News;

import java.util.ArrayList;

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

    /**
     * 添加新闻
     *
     * @param news
     */
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


    public ArrayList<News> queryNewsByCid(int cid, int startnid, int count) {
        ArrayList<News> list = new ArrayList<News>();
        Cursor cursor = db.query("tb_news", null, "cid = ?",
                new String[]{String.valueOf(cid)}, null, null, "ptime desc", "?,?");
        while (cursor.moveToNext()) {
            News news = new News();
            news.setNid(cursor.getInt(cursor.getColumnIndex("nid")));
            news.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            news.setDigest(cursor.getString(cursor.getColumnIndex("digest")));
            news.setSource(cursor.getString(cursor.getColumnIndex("source")));
            news.setPtime(cursor.getString(cursor.getColumnIndex("ptime")));
            news.setCommentCount(cursor.getInt(cursor.getColumnIndex("commentCount")));
            list.add(news);
        }
        return list;
    }
}
