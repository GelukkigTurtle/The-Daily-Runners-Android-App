package com.turtlesoftware.thedailyrunners.main;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.turtlesoftware.thedailyrunners.main.R;
import com.turtlesoftware.thedailyrunners.adapters.FullScreenImageAdapter;
import com.turtlesoftware.thedailyrunners.tools.BundlesKeys;

public class FullScreenViewActivity extends Activity{

	private FullScreenImageAdapter adapter;
	private ViewPager viewPager;
	private ArrayList<String> imagesUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fullscreen_view);

		viewPager = (ViewPager) findViewById(R.id.pager);

		
		
		imagesUrl =	getIntent().getExtras().getStringArrayList(BundlesKeys.BUNDLE_RACE_IMAGE);
		

		Intent i = getIntent();
		int position = i.getIntExtra("position", 0);

		adapter = new FullScreenImageAdapter(FullScreenViewActivity.this,imagesUrl);

		viewPager.setAdapter(adapter);

		// displaying selected image first
		viewPager.setCurrentItem(position);
	}
}