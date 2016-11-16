package com.turtlesoftware.thedailyrunners.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.turtlesoftware.thedailyrunners.main.MenuBaseActivity;
import com.turtlesoftware.thedailyrunners.main.R;
import com.turtlesoftware.thedailyrunners.tools.Configuration;
import com.turtlesoftware.thedailyrunners.tools.Tools;

public class MenuAboutFragment extends Fragment {
	
	public MenuAboutFragment() {
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		// inflamos la vista que queremos mostrar en pantalla
		View rootView = inflater.inflate(R.layout.menu_about, container, false);
		MenuBaseActivity.actionBar.setTitle(getString(R.string.title_about));

		Tools.applyFont(getActivity(), rootView, Configuration.GENERAL_FONT_NAME);

		return rootView;
	}

}
