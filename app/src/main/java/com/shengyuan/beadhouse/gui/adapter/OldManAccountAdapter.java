package com.shengyuan.beadhouse.gui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.glide.GlideLoader;
import com.shengyuan.beadhouse.model.CareOldManListBean;

import java.util.List;

/**
 * 老人账户列表Adapter
 * Created by dell on 2017/11/12.
 */

public class OldManAccountAdapter extends BaseAdapter {
    private List<CareOldManListBean.FocusListBean> list;

    public OldManAccountAdapter(List<CareOldManListBean.FocusListBean> list) {
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
            convertView = View.inflate(parent.getContext(),R.layout.adapter_old_man_account_item,null);
            viewHolder = new ViewHolder();
            viewHolder.init(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CareOldManListBean.FocusListBean bean = (CareOldManListBean.FocusListBean) getItem(position);
        viewHolder.name.setText(bean.getName());
        StringBuilder builder = new StringBuilder(bean.getSex());
        builder.append("  ").append(bean.getAge()).append(parent.getResources().getString(R.string.age));
        viewHolder.sexAndAge.setText(builder.toString());
        GlideLoader.loadNetWorkResource(parent.getContext(),bean.getPhoto(),viewHolder.icon,true);

        return convertView;
    }


    private class ViewHolder{
        public ImageView icon;
        public TextView name;
        public TextView sexAndAge;

        public void init(View rootView){
            icon = rootView.findViewById(R.id.old_man_account_adapter_icon);
            name = rootView.findViewById(R.id.old_man_account_adapter_name);
            sexAndAge = rootView.findViewById(R.id.old_man_account_adapter_sex_and_age);
        }
    }
}
