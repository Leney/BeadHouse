package com.shengyuan.beadhouse.gui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.glide.GlideLoader;
import com.shengyuan.beadhouse.model.CareListBean;

import java.util.List;

/**
 * Created by dell on 2017/11/11.
 */

public class CareListAdapter extends BaseAdapter {
    private List<CareListBean> list;

    public CareListAdapter(List<CareListBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = View.inflate(parent.getContext(),R.layout.adapter_care_list_item,null);
            viewHolder = new ViewHolder();
            viewHolder.init(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(position == list.size() - 1){
            // 是最后一条数据
            viewHolder.icon.setImageResource(R.mipmap.default_user_icon);
            viewHolder.name.setText(parent.getResources().getString(R.string.add_new_old_man));
        }else {
            // 不是最后一条数据
            CareListBean bean = (CareListBean) getItem(position);
            GlideLoader.loadNetWorkResource(parent.getContext(),bean.icon,viewHolder.icon,false);
            viewHolder.icon.setImageResource(R.mipmap.head_icon);
            viewHolder.name.setText(bean.name);
        }

        return convertView;
    }

    private class ViewHolder {
        ImageView icon;
        TextView name;
        public void init(View rootView){
            icon = rootView.findViewById(R.id.care_list_item_icon);
            name = rootView.findViewById(R.id.care_list_item_name);
        }
    }
}
