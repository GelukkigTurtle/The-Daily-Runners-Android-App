package com.turtlesoftware.thedailyrunners.adapters;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.turtlesoftware.thedailyrunners.main.R;

public class SortExpandableAdapter extends BaseExpandableListAdapter {
 
    private Context _context;
    private List<String> mlistDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> mlistDataChild;
 
    public SortExpandableAdapter(Context context, List<String> listDataHeader,
            HashMap<String, List<String>> listChildData) {
        this._context = context;
        this.mlistDataHeader = listDataHeader;
        this.mlistDataChild = listChildData;
    }
 
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.mlistDataChild.get(this.mlistDataHeader.get(groupPosition))
                .get(childPosititon);
    }
 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    @Override
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
 
        final String childText = (String) getChild(groupPosition, childPosition);
 
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.sort_item_row, null);
        }
 
        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.txtSortItem);
 
        txtListChild.setText(childText);
        return convertView;
    }
 
    @Override
    public int getChildrenCount(int groupPosition) {
        return this.mlistDataChild.get(this.mlistDataHeader.get(groupPosition))
                .size();
    }
 
    @Override
    public Object getGroup(int groupPosition) {
        return this.mlistDataHeader.get(groupPosition);
    }
 
    @Override
    public int getGroupCount() {
        return this.mlistDataHeader.size();
    }
 
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.sort_group_row, null);
        }
 
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.txtSortGroup);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
 
        return convertView;
    }
 
    @Override
    public boolean hasStableIds() {
        return false;
    }
 
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
