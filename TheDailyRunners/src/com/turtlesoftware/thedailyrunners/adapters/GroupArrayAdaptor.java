package com.turtlesoftware.thedailyrunners.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.turtlesoftware.thedailyrunners.beans.Group;
import com.turtlesoftware.thedailyrunners.main.R;
import com.turtlesoftware.thedailyrunners.tools.Configuration;
import com.turtlesoftware.thedailyrunners.tools.Tools;

public class GroupArrayAdaptor extends ArrayAdapter<Group>{
		  
	int resource;
	private LayoutInflater inflater;
	List<Group> items;
	
	
	 ImageView my_image;
	    AnimationListener listener;

	public GroupArrayAdaptor(Context context, int resource, List<Group> news) {
		super(context, resource, news);
		this.resource = resource;
		this.items = news;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		listener = new AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                System.out.println("End Animation!");
                //load_animations();
            }
        };

       
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		final Group li = (Group) items.get(position);
		GroupItemHolder  groupHolder;

		if (view == null) {
			groupHolder = new GroupItemHolder();
			view = new LinearLayout(getContext());
			inflater.inflate(resource, (ViewGroup) view, true);
			
			groupHolder.txtGroupName = (TextView) view.findViewById(R.id.txt_group_title);
			groupHolder.imgGroup = (ImageView) view.findViewById(R.id.img_group);

			view.setTag(groupHolder);
		} else {
			groupHolder = (GroupItemHolder) view.getTag();
		}
		Group group = (Group) li;
		
		groupHolder.txtGroupName.setText(group.name);
		
		// create a new progress bar for each image to be loaded
		ProgressBar progressBar = null;
		if (view != null) {
			progressBar = (ProgressBar) view
					.findViewById(R.id.loading_bar_image_group);

		}

		

		Picasso.with(getContext()).load(group.photo)
				.into(groupHolder.imgGroup, new ImageLoadedCallback(progressBar) {
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

	static class GroupItemHolder {
		TextView txtGroupName;
		ImageView imgGroup;
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
