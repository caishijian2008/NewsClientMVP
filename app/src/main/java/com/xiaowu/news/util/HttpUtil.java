package com.xiaowu.news.util;

import android.content.Context;
import android.util.Log;

import com.xiaowu.news.model.News;
import com.xiaowu.news.service.SyncHttp2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by caishijian on 16-8-2.
 */
public class HttpUtil {
    private static final String TAG = "main";
    private final int SUCCESS = 0;                        // 加载新闻成功
    private final int NONEWS = 1;                        // 没有新闻
    private final int NOMORENEWS = 2;                    // 没有更多新闻
    private final int LOADERROR = 3;                    // 加载失败

    private Context context;
    private DBHelper dbHelper;
    private SyncHttp2 syncHttp;
    private static String url = null;

    public HttpUtil(Context context) {
        this.context = context;
        dbHelper = DBHelper.getInstance(context);
    }

    /**
     * 获取所有的新闻
     */
    public void getAllNewsToDb() {

        String url = "http://192.168.0.101:8080/NewsClientServer/getAllNews";
        Log.i(TAG, "url:--->" + url);
        syncHttp = new SyncHttp2();
        try {
            // 通过Http协议发送Get请求，返回字符串
            String retStr = syncHttp.httpGet(url, null);
            Log.i(TAG, "retStr: " + retStr);
            JSONObject jsonObject = new JSONObject(retStr);
            int retCode = jsonObject.getInt("ret");
            if (retCode == 0) {
                JSONObject dataObj = jsonObject.getJSONObject("data");
                // 获取返回数目
                int totalNum = dataObj.getInt("totalnum");
                if (totalNum > 0) {
                    // 获取返回新闻集合
                    JSONArray newslistArray = dataObj.getJSONArray("news");
                    // 将用JSON格式解析的数据添加到数据集合当中
                    for (int i = 0; i < newslistArray.length(); i++) {
                        JSONObject newsObject = (JSONObject) newslistArray
                                .opt(i);
                        Log.i(TAG, "getAllNewsToDb: " + newsObject.getString("title"));///////////
                        News news = new News();
                        news.setNid(newsObject.getInt("nid"));
                        news.setCid(newsObject.getInt("cid"));
                        news.setTitle(newsObject.getString("title"));
                        news.setDigest(newsObject.getString("digest"));
                        news.setBody(newsObject.getString("body"));
                        news.setSource(newsObject.getString("source"));
                        news.setCommentCount(newsObject.getInt("commentcount"));
                        news.setPtime(newsObject.getString("ptime"));
                        news.setImgSrc(newsObject.getString("imgsrc"));
                        news.setDeleted(0);
                        dbHelper.addNews(news);
                    }
                } else {
                    Log.i(TAG, "没有新闻!");
                    throw new Exception("没有新闻!");
                }
            } else {
                Log.i(TAG, "加载新闻失败!");
                throw new Exception("加载新闻失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "加载新闻失败!!");

        }
    }

    public int getSpecCatNews(int cid, List<HashMap<String, Object>> newsList,
                              int startnid, int newsCount, boolean firstTime) {
        // 如果是第一次加载的话
		if (firstTime) {
			newsList.clear();
		}

        ArrayList<News> arrayList = new ArrayList<News>();
        try {
            arrayList = dbHelper.queryNewsByCid(cid, startnid, newsCount);
            for (News news : arrayList) {
                HashMap<String, Object> hashMap = new HashMap<String, Object>();
                hashMap.put("nid", news.getNid());
                hashMap.put("newslist_item_title",
                        news.getTitle());
                hashMap.put("newslist_item_digest",
                        news.getDigest());
                hashMap.put("newslist_item_source",
                        news.getSource());
                hashMap.put("newslist_item_ptime",
                        news.getPtime());
                hashMap.put("newslist_item_comments",
                        news.getCommentCount());
                newsList.add(hashMap);
            }
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return LOADERROR;
        }



    }
}
