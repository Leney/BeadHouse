package com.shengyuan.beadhouse.gui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.glide.GlideLoader;
import com.shengyuan.beadhouse.model.PhysiologyBean;

import java.util.List;

/**
 * Created by dell on 2017/11/17.
 */

public class PhysiologyAdapter extends BaseExpandableListAdapter {
    private List<String> groupList;
    private List<List<PhysiologyBean>> childList;

    public PhysiologyAdapter(List<String> groupList, List<List<PhysiologyBean>> childList) {
        this.groupList = groupList;
        this.childList = childList;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder viewHolder;
        if(convertView == null){
            convertView = View.inflate(parent.getContext(),R.layout.adapter_physiology_group,null);
            viewHolder = new GroupViewHolder();
            viewHolder.init(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (GroupViewHolder) convertView.getTag();
        }
        viewHolder.date.setText((String) getGroup(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder;
        if(convertView == null){
            convertView = View.inflate(parent.getContext(),R.layout.adapter_physiology_child,null);
            viewHolder = new ChildViewHolder();
            viewHolder.init(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }

        PhysiologyBean bean = (PhysiologyBean) getChild(groupPosition,childPosition);
        GlideLoader.loadNetWorkResource(parent.getContext(),bean.icon,viewHolder.icon,true);
        viewHolder.name.setText(bean.name);
        viewHolder.value.setText(bean.value);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class GroupViewHolder{
        TextView date;
        public void init(View rootView){
            date = rootView.findViewById(R.id.physiology_adapter_group_name);
        }
    }

    private class ChildViewHolder{
        ImageView icon;
        TextView name,value;
        public void init(View rootView){
            icon = rootView.findViewById(R.id.physiology_adapter_child_icon);
            name = rootView.findViewById(R.id.physiology_adapter_child_name);
            value = rootView.findViewById(R.id.physiology_adapter_child_value);
        }
    }
}
