package com.shengyuan.beadhouse.gui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;

import java.util.List;

/**
 * 优惠券Adapter
 * Created by dell on 2017/11/12.
 */

public class SpinnerAdapter extends BaseAdapter {
    private List<String> list;

    public SpinnerAdapter(List<String> list) {
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
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.adapter_spinner_item, null);
            viewHolder = new ViewHolder();
            viewHolder.init(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText((String) getItem(position));

        return convertView;
    }


    private class ViewHolder {
        public TextView name;
        public void init(View rootView) {
            name = rootView.findViewById(R.id.spinner_item_name);
        }
    }
}
