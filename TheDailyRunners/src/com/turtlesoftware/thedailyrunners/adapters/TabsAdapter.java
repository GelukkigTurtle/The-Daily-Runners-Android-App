package com.turtlesoftware.thedailyrunners.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.turtlesoftware.thedailyrunners.fragments.GalleryFragment;
import com.turtlesoftware.thedailyrunners.fragments.GroupsFragment;
import com.turtlesoftware.thedailyrunners.fragments.NewsFragment;
import com.turtlesoftware.thedailyrunners.fragments.RacesFragment;
/**
 * @author Freddy Ayala
 * @since 2014
 * Adaptador de Tabs , instancia los fragments para cada Tab.	
 */
public class TabsAdapter extends FragmentPagerAdapter{
	
	private FragmentManager mFragmentManager;
	public static GalleryFragment galleryFrag;
    private static final String[] CONTENT = new String[] { "Carreras", "Interés", "Grupos" ,"Galería"};

	public TabsAdapter(FragmentManager fm) {
		super(fm);
		mFragmentManager = fm;	

	}

	@Override
	public Fragment getItem(int index) {
        Fragment fragment = mFragmentManager.findFragmentById(android.R.id.content);

		if(index < 5){
			switch (index) {
			case 0:
				 // Check if this Fragment already exists.
		        /*String name = makeFragmentName(R.layout.race_layout , index);
		        Fragment f = mFragmentManager.findFragmentByTag(name);
		        if(f == null){
		         f = RacesFragment.newInstance("Races list");
		        }*/
				fragment = RacesFragment.newInstance("Races list");
		        
				return fragment;
			case 1:
				return NewsFragment.newInstance();
			case 2:
				return GroupsFragment.newInstance();
			case 3:
				galleryFrag = GalleryFragment.newInstance();
				return galleryFrag;
			}
		}
		return null;
	}

	@Override
	public int getCount() {
		//for 3 tabs
		return 4;
	}

    @Override
    public CharSequence getPageTitle(int position) {
    	
        return CONTENT[position % CONTENT.length].toUpperCase();
    }


	
	@SuppressWarnings("unused")
	private static String makeFragmentName(int viewId, int index) {
	    return "android:switcher:" + viewId + ":" + index;
	}
	
	

}
