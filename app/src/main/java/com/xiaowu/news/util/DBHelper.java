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
    /**
	 * 获取指定类型的新闻列表
    private int getSpecCatNews(int cid, List<HashMap<String, Object>> newsList,
                               int startnid, boolean firstTime) {

        //http://192.168.0.101:8080/NewsClientServer/getSpecifyCategoryNews?startnid=0&count=10&cid=1
        String url = "http://192.168.0.101:8080/NewsClientServer/getSpecifyCategoryNews";
        String params = "startnid=" + startnid + "&count=" + NEWSCOUNT
                + "&cid=" + cid;
        SyncHttp2 syncHttp = new SyncHttp2();

        try {
            // 通过Http协议发送Get请求，返回字符串
            String retStr = syncHttp.httpGet(url, params);
            JSONObject jsonObject = new JSONObject(retStr);
            int retCode = jsonObject.getInt("ret");
            if (retCode == 0) {
                JSONObject dataObj = jsonObject.getJSONObject("data");
                // 获取返回数目
                int totalNum = dataObj.getInt("totalnum");
                if (totalNum > 0) {
                    // 获取返回新闻集合
                    JSONArray newslistArray = dataObj.getJSONArray("newslist");
                    // 将用JSON格式解析的数据添加到数据集合当中
                    for (int i = 0; i < newslistArray.length(); i++) {
                        JSONObject newsObject = (JSONObject) newslistArray
                                .opt(i);
                        HashMap<String, Object> hashMap = new HashMap<String, Object>();
                        hashMap.put("nid", newsObject.getInt("nid"));
                        hashMap.put("newslist_item_title",
                                newsObject.getString("title"));
                        hashMap.put("newslist_item_digest",
                                newsObject.getString("digest"));
                        hashMap.put("newslist_item_source",
                                newsObject.getString("source"));
                        hashMap.put("newslist_item_ptime",
                                newsObject.getString("ptime"));
                        hashMap.put("newslist_item_comments",
                                newsObject.getInt("commentcount"));
                        newsList.add(hashMap);
                    }
                    return SUCCESS;
                } else {
                    //第一次加载新闻列表
                    if (firstTime) {
                        return NONEWS;			//没有新闻
                    } else {
                        return NOMORENEWS;		//没有更多新闻
                    }
                }
            } else {
                return LOADERROR;			//加载新闻失败
            }
        } catch (Exception e) {
            e.printStackTrace();
            return LOADERROR;			//加载新闻失败
        }
    }
    */

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
            values.put("cid", news.getCid());
            values.put("title", news.getTitle());
            values.put("digest", news.getDigest());
            values.put("body", news.getBody());
            values.put("source", news.getSource());
            values.put("commentCount", news.getCommentCount());
            values.put("ptime", news.getPtime());
            values.put("imgSrc", news.getImgSrc());
            values.put("deleted", news.isDeleted());
            db.insert("tb_news", null, values);
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
