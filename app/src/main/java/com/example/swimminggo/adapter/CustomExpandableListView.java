package com.example.swimminggo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.swimminggo.R;

import java.util.HashMap;
import java.util.List;

public class CustomExpandableListView extends BaseExpandableListAdapter {

    Context context;
    List<String> listHeader;
    HashMap<String,List<String>> listChild;

    public CustomExpandableListView(Context context, List<String> listHeader, HashMap<String, List<String>> listChild) {
        this.context = context;
        this.listHeader = listHeader;
        this.listChild = listChild;
    }

    @Override
    public int getGroupCount() {
        return listHeader.size();
    }

    @Override
    public int getChildrenCount(int groupP) {
        return listChild.get(listHeader.get(groupP)).size();
    }

    @Override
    public Object getGroup(int groupP) {
        return listHeader.get(groupP);
    }

    @Override
    public Object getChild(int groupP, int childP) {
        return listChild.get(listHeader.get(groupP)).get(childP);
    }

    @Override
    public long getGroupId(int groupP) {
        return groupP;
    }

    @Override
    public long getChildId(int groupP, int childP) {
        return childP;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupP, boolean b, View converview, ViewGroup viewGroup) {
        String headerTitle = (String) getGroup(groupP);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        converview = inflater.inflate(R.layout.group_view,null);
        TextView txtHeader = (TextView) converview.findViewById(R.id.textViewHeader);
        txtHeader.setText(headerTitle);
        return converview;
    }

    @Override
    public View getChildView(int groupP, int childP, boolean b, View converview, ViewGroup viewGroup) {
        String item = (String) getChild(groupP,childP);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        converview = inflater.inflate(R.layout.child_view,null);
        TextView txtItem = (TextView) converview.findViewById(R.id.textViewChild);
        txtItem.setText(item);
        return converview;
    }

    @Override
    public boolean isChildSelectable(int groupP, int childP) {
        return true;
    }
}
