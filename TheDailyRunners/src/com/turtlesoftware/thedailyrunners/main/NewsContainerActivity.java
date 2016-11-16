package com.turtlesoftware.thedailyrunners.main;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.turtlesoftware.thedailyrunners.main.R;
import com.turtlesoftware.thedailyrunners.beans.News;
import com.turtlesoftware.thedailyrunners.tools.BundlesKeys;
import com.turtlesoftware.thedailyrunners.tools.Tools;

public class NewsContainerActivity extends SlidingFragmentActivity implements  ActionBar.TabListener { 
	
	public News newsBean;
	protected FrameLayout webViewPlaceholder;
	protected WebView webView;

	
	@Override
	public  void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_container);
		
		 // set the Behind View
		setBehindContentView(R.layout.sliding_menu);
		android.support.v4.app.FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
		t.commit();
		
		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);

		//set action bar
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		setSlidingActionBarEnabled(false);
	        
		
		//final ProgressDialog pd = ProgressDialog.show(this, "", this.getString(R.string.v_loading),true);
	    //pd.setCancelable(false);
		
		//Obtein Bundle data from a news topic
		newsBean = getIntent().getExtras().getParcelable(BundlesKeys.BUNDLE_INFO_NEWS);
		 
	    // Initialize the UI
	     initUI();
	     
	     /*webView.setWebViewClient(new WebViewClient() {
	    	    @Override
	    	    public void onPageFinished(WebView view, String url) {
	    	        if (pd != null && pd.isShowing()) {
	    	            pd.dismiss();
	    	        }
	    	    }
	    	    
	    });*/
	 	
	     final Activity activity = this;
	     webView.setWebChromeClient(new WebChromeClient(){

	         public void onProgressChanged(WebView view, int progress) {
	                 activity.setTitle(getResources().getString(R.string.v_loading)+progress+"%");
	                 activity.setProgress(progress * 100);
	                    if(progress == 100)
	                       activity.setTitle(getResources().getString(R.string.app_name));
	                 }
	     	});
	     
	   //change title app font
	  Tools.changeTitleAppFont(this, NewsContainerActivity.this);
		
	}
	
	@SuppressLint("SetJavaScriptEnabled") protected void initUI() {
		// Retrieve UI elements
		webViewPlaceholder = ((FrameLayout) findViewById(R.id.webViewPlaceholder));

		// Initialize the WebView if necessary
		if (webView == null) {
			// Create the webview
			webView = new WebView(this);
			webView.setLayoutParams(new ViewGroup.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			webView.getSettings().setSupportZoom(true);
			webView.getSettings().setBuiltInZoomControls(true);
			webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
			webView.setScrollbarFadingEnabled(true);
			webView.getSettings().setLoadsImagesAutomatically(true);
			webView.getSettings().setJavaScriptEnabled(true);

			// Load the URLs inside the WebView, not in the external web browser
			webView.setWebViewClient(new WebViewClient());

			// Load a page
			webView.loadUrl(newsBean.url);
		}

		// Attach the WebView to its placeholder
		webViewPlaceholder.addView(webView);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		if (webView != null) {
			// Remove the WebView from the old placeholder
			webViewPlaceholder.removeView(webView);
		}

		super.onConfigurationChanged(newConfig);

		// Load the layout resource for the new configuration
		setContentView(R.layout.news_container);

		// Reinitialize the UI
		initUI();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		// Save the state of the WebView
		webView.saveState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		// Restore the state of the WebView
		webView.restoreState(savedInstanceState);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
