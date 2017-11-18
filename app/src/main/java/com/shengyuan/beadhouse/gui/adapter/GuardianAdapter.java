package com.shengyuan.beadhouse.gui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.glide.GlideLoader;
import com.shengyuan.beadhouse.model.GuardianBean;

import java.util.List;

/**
 * 监护人列表Adapter
 * Created by dell on 2017/11/12.
 */

public class GuardianAdapter extends BaseAdapter {
    private List<GuardianBean> list;

    public GuardianAdapter(List<GuardianBean> list) {
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
            convertView = View.inflate(parent.getContext(),R.layout.adapter_guardian_item,null);
            viewHolder = new ViewHolder();
            viewHolder.init(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        GuardianBean bean = (GuardianBean) getItem(position);
        viewHolder.name.setText(bean.name);
        viewHolder.relationship.setText(bean.relationship);
        viewHolder.phone.setText(bean.phone);
        // TODO 还要设置头像
        GlideLoader.loadNetWorkResource(parent.getContext(),bean.icon,viewHolder.icon,false);

        return convertView;
    }


    private class ViewHolder{
        public ImageView icon;
        public TextView name;
        public TextView relationship;
        public TextView phone;

        public void init(View rootView){
            icon = rootView.findViewById(R.id.guardian_adapter_icon);
            name = rootView.findViewById(R.id.guardian_adapter_name);
            relationship = rootView.findViewById(R.id.guardian_adapter_relationship);
            phone = rootView.findViewById(R.id.guardian_adapter_phone);
        }
    }
}
