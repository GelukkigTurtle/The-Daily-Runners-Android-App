package com.turtlesoftware.thedailyrunners.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.turtlesoftware.thedailyrunners.main.R;
import com.turtlesoftware.thedailyrunners.main.MenuBaseActivity;
import com.turtlesoftware.thedailyrunners.tools.Configuration;
import com.turtlesoftware.thedailyrunners.tools.Tools;

public class MenuFragment extends ListFragment {
	
	public static Fragment contentSelected;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		SampleAdapter adapter = new SampleAdapter(getActivity());
		String[] menuItems = getResources().getStringArray(R.array.menu_items);
//		for (int i = 0; i < menuItems.length ; i++) {
//			adapter.add(new SampleItem(menuItems[i], android.R.drawable.ic_menu_search));
//		}
		adapter.add(new SampleItem(menuItems[0], R.drawable.calendar48));
		adapter.add(new SampleItem(menuItems[1], R.drawable.account48));
		adapter.add(new SampleItem(menuItems[2], R.drawable.about48));

		
		setListAdapter(adapter);
		
	}

	private class SampleItem {
		public String tag;
		public int iconRes;
		public SampleItem(String tag, int iconRes) {
			this.tag = tag; 
			this.iconRes = iconRes;
		}
	}

	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, null);
			}
			ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView.findViewById(R.id.row_title);
			title.setText(getItem(position).tag.toUpperCase());
			
			Tools.applyFont(getContext(), convertView, Configuration.TITLE_FONT_NAME);

			return convertView;
		}

	}
	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {
		Fragment newContent = null;
		switch (position) {
		case 0:
			newContent = new MenuMyRacesFragment();
			break;
		case 1:
			newContent = new MenuSocialFragment();
			//startSocialActivity(v);
			break;
		case 2:
			newContent = new MenuAboutFragment();
			break;
		}
		if (newContent != null){
			contentSelected = newContent;
			switchFragment(newContent,v);
		}
	}

	
	// the meat of switching the above fragment
	private void switchFragment(Fragment fragment, View v) {

		if (!MenuBaseActivity.active) {
			Intent intent = new Intent(v.getContext(), MenuBaseActivity.class);
			startActivity(intent);

		}

		if (getActivity() instanceof MenuBaseActivity) {
			MenuBaseActivity fca = (MenuBaseActivity) getActivity();
			fca.switchContent(fragment);
		}
	}

}
