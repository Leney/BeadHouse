package com.shengyuan.beadhouse.gui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.model.CarePackageBean;

import java.util.List;

/**
 * 照护套餐Adapter
 * Created by dell on 2017/11/11.
 */

public class CarePackageAdapter extends BaseAdapter {
    private List<CarePackageBean> list;

    public CarePackageAdapter(List<CarePackageBean> list) {
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
            convertView = View.inflate(parent.getContext(), R.layout.adapter_care_package_item, null);
            viewHolder = new ViewHolder();
            viewHolder.init(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        CarePackageBean bean = (CarePackageBean) getItem(position);
        viewHolder.name.setText(bean.name);
        viewHolder.info.setText(bean.info);
        viewHolder.beginTime.setText(parent.getResources().getString(R.string.begin_time)+bean.beginTime);
        viewHolder.endTime.setText(parent.getResources().getString(R.string.end_time)+bean.endTime);
        viewHolder.progressBar.setProgress(bean.progress);

        return convertView;
    }

    private class ViewHolder {
        TextView name, info, beginTime, endTime;
        ProgressBar progressBar;

        public void init(View rootView) {
            name = rootView.findViewById(R.id.care_package_adapter_name);
            info = rootView.findViewById(R.id.care_package_adapter_info);
            beginTime = rootView.findViewById(R.id.care_package_adapter_begin_time);
            endTime = rootView.findViewById(R.id.care_package_adapter_end_time);
            progressBar = rootView.findViewById(R.id.care_package_adapter_progress);
        }
    }
}
