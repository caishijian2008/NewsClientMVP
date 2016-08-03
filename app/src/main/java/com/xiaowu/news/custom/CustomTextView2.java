package com.xiaowu.news.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.xiaowu.news.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by caishijian on 16-7-28.
 */
public class CustomTextView2 extends LinearLayout {

    private static final String TAG = "main";
    //上下文对象
    private Context mContext;
    //声明TypedArray的引用
    private TypedArray mTypedArray;
    //布局参数
    private LayoutParams params;

    public CustomTextView2(Context context) {
        super(context);
    }

    public CustomTextView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        //从attrs.xml文件中那个获取自定义属性
        mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.customTextView);
    }

    public void setText(ArrayList<HashMap<String, Object>> datas) {
        //遍历ArrayList
        for (HashMap<String, Object> hashMap : datas) {
            //获取key为"imgsrc"的值
            String imgsrc = (String) hashMap.get("imgsrc");
            String bd = hashMap.get("body").toString();
            String bd2 = (String) hashMap.get("body");
            Log.i(TAG, "bd-->"+bd);
            Log.i(TAG, "bd2-->"+bd2);
            //获取自定义属性属性
            final ImageView imageView = new ImageView(mContext);
            if (imgsrc != null || imgsrc != "") {
                int imagewidth = mTypedArray.getDimensionPixelOffset(R.styleable.customTextView_image_width, 200);
                int imageheight = mTypedArray.getDimensionPixelOffset(R.styleable.customTextView_image_height, 200);
                params = new LayoutParams(imagewidth, imageheight);
                params.gravity = Gravity.CENTER_HORIZONTAL;    //居中
                imageView.setLayoutParams(params);
                //显示图片
                RequestQueue queue = Volley.newRequestQueue(mContext);
                ImageRequest request = new ImageRequest(imgsrc, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_INSIDE, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        imageView.setImageResource(R.drawable.ic_launcher);
                    }
                });
                request.setTag("imgResource");
                queue.add(request);
                //将imageView添加到LinearLayout当中
                addView(imageView);
            } else {
                imageView.setImageResource(R.drawable.ic_launcher);
                imageView.setVisibility(GONE);
                addView(imageView);
            }
            float textSize = mTypedArray.getDimension(R.styleable.customTextView_textSize, 16);
            int textColor = mTypedArray.getColor(R.styleable.customTextView_textColor, 0xFF000000);

            TextView textView = new TextView(mContext);
            textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
            Log.i(TAG, "setText: "+Html.fromHtml((String) hashMap.get("body")));
            textView.setText(Html.fromHtml((String) hashMap.get("body")));
//            textView.setText("hahahhahhahhahaahhaahah");
            textView.setTextSize(textSize);		//设置字体大小
            textView.setTextColor(textColor);	//设置字体颜色
            //把TextView添加到自定义LinearLayout中
            addView(textView);
        }
    }
}
