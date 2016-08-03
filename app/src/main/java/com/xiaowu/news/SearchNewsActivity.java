package com.xiaowu.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SearchNewsActivity extends Activity {
	private Button searchButton;
	private EditText searchText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_news_layout);
		initView();

		searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SearchNewsActivity.this, SearchNewsDetailsActivity.class);
//				intent.setData(Uri.parse("http://news.baidu.com/"));
//				intent.setAction(Intent.ACTION_VIEW);
				Bundle bundle = new Bundle();
				bundle.putString("text", searchText.getText().toString());
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	private void initView() {
		searchButton = (Button) findViewById(R.id.search_button);
		searchText = (EditText) findViewById(R.id.search_news_txt);
	}
}
