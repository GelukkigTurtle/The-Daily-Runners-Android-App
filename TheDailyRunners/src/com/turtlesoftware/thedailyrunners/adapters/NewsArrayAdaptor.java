package com.turtlesoftware.thedailyrunners.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.turtlesoftware.thedailyrunners.beans.News;
import com.turtlesoftware.thedailyrunners.main.R;
import com.turtlesoftware.thedailyrunners.tools.Configuration;
import com.turtlesoftware.thedailyrunners.tools.Tools;

public class NewsArrayAdaptor extends ArrayAdapter<News>{
	  
	  int resource;
	  private LayoutInflater inflater;
	  List<News> items;
	  
	  
	public NewsArrayAdaptor(Context context, int resource, List<News> news) {
		super(context, resource, news);
		this.resource = resource;
		this.items = news;
		
		
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		final News li = (News) items.get(position);
		NewsItemHolder raceHolder;

		if (view == null) {
			raceHolder = new NewsItemHolder();
			view = new LinearLayout(getContext());
			inflater.inflate(resource, (ViewGroup) view, true);
			raceHolder.txtNewsDate = (TextView) view.findViewById(R.id.txt_news_date);
			raceHolder.txtNewsTitle = (TextView) view.findViewById(R.id.txt_news_title);
			raceHolder.imgNews = (ImageView) view.findViewById(R.id.img_news);
			
			view.setTag(raceHolder);
		} else {
			raceHolder = (NewsItemHolder) view.getTag();
		}
		News news = (News) li;

		raceHolder.txtNewsDate.setText(Configuration.dateformat.format(news.dateCreated));
		raceHolder.txtNewsTitle.setText(news.title);
		
		//create a new progress bar for each image to be loaded
		ProgressBar progressBar = null;
		if (view != null) {
		   progressBar = (ProgressBar) view.findViewById(R.id.loading_bar_image_news);
		   
		}


		//load the image url with a callback to a callback method/class
		Picasso.with(getContext())
		            .load(news.photo)
		            .into(raceHolder.imgNews,  new ImageLoadedCallback(progressBar) {
		                    @Override
		                    public void onSuccess() {
		                        if (this.progressBar != null) {
		                            this.progressBar.setVisibility(View.INVISIBLE);
		                         }
		                    }
		              });
		
		Tools.applyFont(getContext(), view, Configuration.GENERAL_FONT_NAME);

		return view;
	}
	
	static class NewsItemHolder {
		TextView txtNewsDate;
		TextView txtNewsTitle;
		ImageView imgNews;
	}
	
	private class ImageLoadedCallback implements Callback {
		ProgressBar progressBar;

		public ImageLoadedCallback(ProgressBar progBar) {
			progressBar = progBar;
		}

		@Override
		public void onSuccess() {

		}

		@Override
		public void onError() {

		}
	}
	

}

