package com.turtlesoftware.thedailyrunners.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.turtlesoftware.thedailyrunners.main.R;
import com.turtlesoftware.thedailyrunners.main.MainActivity;
import com.turtlesoftware.thedailyrunners.tools.URLinks;

public class GalleryFragment extends Fragment{

	static MainActivity mMainActivity;
	static ProgressDialog dialog;
	public static  ProgressBar pBar;
	static View rootView;
	
	 static WebView webView;


	public static GalleryFragment newInstance(){
		GalleryFragment frag = new GalleryFragment();
		
		return frag;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

	    setHasOptionsMenu(true);

		// inflamos la vista que queremos mostrar en pantalla
	    rootView = inflater.inflate(R.layout.gallery_layout, container, false);
		// inflamos sus componentes
		pBar = (ProgressBar) rootView.findViewById(R.id.loading_time_progress_bar_gallery);
		webView= (WebView) rootView.findViewById(R.id.webGallery);
		mMainActivity = (MainActivity) getActivity();
		
		return rootView;
	}
	
	
	@SuppressLint("SetJavaScriptEnabled") public static void setDataList(){

		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		webView.setScrollbarFadingEnabled(true);
		webView.getSettings().setLoadsImagesAutomatically(true);
		webView.getSettings().setJavaScriptEnabled(true);

		// Load the URLs inside the WebView, not in the external web browser
		webView.setWebViewClient(new WebViewClient(){
			@Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
				try {
					Intent intent = new Intent(Intent.ACTION_VIEW,
					    Uri.parse(url));
					mMainActivity.startActivity(intent);

					}catch (Exception e) {
						mMainActivity.startActivity(new Intent(Intent.ACTION_VIEW,
					         Uri.parse(url))); 
					} 
                return true;
            }
			
			
		});
		webView.setWebChromeClient(new WebChromeClient(){

	         public void onProgressChanged(WebView view, int progress) {
	        	pBar.setIndeterminate(true);
	     		pBar.setVisibility(ProgressBar.VISIBLE);
	                    if(progress == 100){
	                    	pBar.setIndeterminate(false);
	        	     		pBar.setVisibility(ProgressBar.GONE);	    
	        	     	}
	                 }
	     	});

		// Load a page
		webView.loadUrl(URLinks.URL_GALLERY);
		
		
	}
	public boolean canGoBack() {
        return GalleryFragment.webView != null && GalleryFragment.webView.canGoBack();
    }

    public void goBack() {
        if(GalleryFragment.webView != null) {
            GalleryFragment.webView.goBack();  
        }  
    }
    
    @Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		// Save the state of the WebView
		if(webView != null){
		 webView.saveState(outState);
		}
	}
    
    @Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    inflater.inflate(R.menu.refresh_main, menu);
	    super.onCreateOptionsMenu(menu,inflater);
	}
}