package com.shengyuan.beadhouse.gui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.model.CareServiceBean;

import java.util.List;

/**
 * 首页服务总览Adapter
 * Created by dell on 2017/11/7.
 */

public class ServiceItemAdapter extends BaseAdapter {
    private List<CareServiceBean> list;

    public ServiceItemAdapter(List<CareServiceBean> list) {
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
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = View.inflate(parent.getContext(),R.layout.adaper_care_service_item,null);
            viewHolder = new ViewHolder();
            viewHolder.init(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CareServiceBean bean = (CareServiceBean) getItem(position);
        viewHolder.name.setText(bean.name);
        viewHolder.describe.setText(bean.describe);
        viewHolder.icon.setImageResource(bean.iconRes);
        return convertView;
    }

    private class ViewHolder {
        TextView name,describe;
        ImageView icon;
        public void init(View view){
            name = view.findViewById(R.id.service_adapter_name);
            describe = view.findViewById(R.id.service_adapter_describe);
            icon = view.findViewById(R.id.service_adapter_icon);
        }
    }
}
