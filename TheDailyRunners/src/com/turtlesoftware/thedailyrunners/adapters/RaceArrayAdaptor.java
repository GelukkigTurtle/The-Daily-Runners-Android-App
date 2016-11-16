package com.turtlesoftware.thedailyrunners.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.turtlesoftware.thedailyrunners.beans.Race;
import com.turtlesoftware.thedailyrunners.beans.SectionItem;
import com.turtlesoftware.thedailyrunners.interfaces.Item;
import com.turtlesoftware.thedailyrunners.main.R;
import com.turtlesoftware.thedailyrunners.tools.Configuration;
import com.turtlesoftware.thedailyrunners.tools.Tools;


/**
 * @author Freddy Ayala
 * @since 2014
 * Adaptador de arreglo de las carreras, para mostrar en  carrera_listview_custom_row.xml en forma de listado
 */

public class RaceArrayAdaptor extends ArrayAdapter<Item>{
	 // private static final String LOG_TAG = RaceArrayAdaptor.class.getCanonicalName();
	  
	  int resource;
	  private LayoutInflater inflater;
	  List<Item> items;
	 	  

	    private static final int TYPE_ITEM = 0;
	    private static final int TYPE_SECTION = 1;
	  
	public RaceArrayAdaptor(Context context, int resource, List<Item> races) {
		super(context, resource, races);
		this.resource = resource;
		this.items = races;
		
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	 @Override
	    public int getItemViewType(int position) {
	        return items.get(position).isSection() ? TYPE_SECTION : TYPE_ITEM;
	    }

	    @Override
	    public int getViewTypeCount() {
	        return 2;  // sectionheader and regular item
	    }
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int type = getItemViewType(position);
		View raceView = convertView;
		final Item li = items.get(position);
		RaceItemHolder raceHolder;
		SectionItemHolder sectionHolder;

		switch (type) {
		case TYPE_SECTION: // is sectionheader
			if (raceView == null) {
				sectionHolder = new SectionItemHolder();
				raceView = new LinearLayout(getContext());
				inflater.inflate(R.layout.race_item_month_section,(ViewGroup) raceView, true);
				raceView.setOnClickListener(null);
				raceView.setOnLongClickListener(null);
				raceView.setLongClickable(false);
				sectionHolder.txtSection = (TextView) raceView.findViewById(R.id.list_item_section_text);
				raceView.setTag(sectionHolder);
			} else {
				sectionHolder = (SectionItemHolder) raceView.getTag();
			}
			SectionItem si = (SectionItem) li;
			sectionHolder.txtSection.setTextColor(getContext().getResources().getColor(R.color.month_text_color));
			sectionHolder.txtSection.setTag("number");
			sectionHolder.txtSection.setText(si.title);
			break;
		case TYPE_ITEM:// no sectionheader
			if (raceView == null) {

				raceHolder = new RaceItemHolder();
				raceView = new LinearLayout(getContext());
				inflater.inflate(resource, (ViewGroup) raceView, true);
				raceHolder.txtDate = (TextView) raceView.findViewById(R.id.txt_race_date);
				raceHolder.txtDistance = (TextView) raceView.findViewById(R.id.txt_race_distance);
				raceHolder.txtName = (TextView) raceView.findViewById(R.id.txt_race_name);
				raceHolder.txtStartTime = (TextView) raceView.findViewById(R.id.txt_race_time);
				raceHolder.txtCountry = (TextView) raceView.findViewById(R.id.txt_country);
				raceView.setTag(raceHolder);
			} else {
				raceHolder = (RaceItemHolder) raceView.getTag();
			}
			Race race = (Race) li;
			

			raceHolder.txtDate.setText(Configuration.calendardateformat.format(race.date));
			raceHolder.txtName.setText(race.name);
			raceHolder.txtStartTime.setText(Configuration.hourformat.format(race.startTime));
			raceHolder.txtDistance.setText(race.distance);
			raceHolder.txtCountry.setText(race.country);
			break;

		}
		Tools.applyFont(getContext(), raceView, Configuration.GENERAL_FONT_NAME);

		return raceView;
	}

	
	
	static class RaceItemHolder {
		TextView txtDate;
		TextView txtName;
		TextView txtStartTime;
		TextView txtDistance;
		TextView txtCountry;
	}
	static class SectionItemHolder {
		TextView txtSection;
	}

	

}
