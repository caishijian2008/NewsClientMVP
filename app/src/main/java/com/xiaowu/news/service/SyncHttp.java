package com.xiaowu.news.service;

import android.util.Log;

import com.xiaowu.news.model.Parameter;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by caishijian on 16-7-1.
 */
public class SyncHttp {
    private static final String TAG = "main";
    private OkHttpClient mOkHttpClient = null;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    /**
     * 通过Get方式发送请求
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public String httpGet(String url, String params) throws Exception {
//        String resp = null;
        //拼接请求URl
        if(null != params && !params.equals("")) {
            url += "?" + params;
        }

        Log.i(TAG, "httpGet: url-->"+url);
        //创建okHttpClient对象
        mOkHttpClient = new OkHttpClient();
        //创建一个Request
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        // new call
        Response response = mOkHttpClient.newCall(request).execute();
        Log.i(TAG, "httpGet: response-->"+response);
        Log.i(TAG, "httpGet: response body1-->"+response.body());
        Log.i(TAG, "httpGet: response body2-->"+response.body().string());
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            return response.message().toString();
        }

    }

    /**
     * 通过post方式发送请求
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public String httpPost(String url, List<Parameter> params) throws Exception {
        mOkHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("nid", params.get(0).getValue())
                .add("region", params.get(1).getValue())
                .add("content", params.get(2).getValue())
                .build();
        Log.i(TAG, "httpPost: \"content\", "+params.get(2).getValue());
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = mOkHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string().toString();
        }else {
            return response.message().toString();
        }
    }

}
