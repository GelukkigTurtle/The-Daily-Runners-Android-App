package com.turtlesoftware.thedailyrunners.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.turtlesoftware.thedailyrunners.fragments.RaceDetailsTabFragment;
import com.turtlesoftware.thedailyrunners.fragments.RaceInfoTabFragment;

public class RaceTabsAdapter  extends FragmentPagerAdapter{
	
    private static final String[] CONTENT = new String[] { "Banner", "Info" };

	
	public RaceTabsAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public Fragment getItem(int index) {
		if(index < 3){
			switch (index) {
			case 0:
				return RaceInfoTabFragment.newInstance();
			case 1:
				return RaceDetailsTabFragment.newInstance();
			
			}
		}
		return null;
	}

	@Override
	public int getCount() {
		//son 2 tabs
		return 2;

	
	}

	@Override
	public CharSequence getPageTitle(int position) {

		return CONTENT[position % CONTENT.length].toUpperCase();
	}
}
