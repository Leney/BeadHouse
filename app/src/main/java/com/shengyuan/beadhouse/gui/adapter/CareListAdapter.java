package com.shengyuan.beadhouse.gui.adapter;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.glide.GlideLoader;
import com.shengyuan.beadhouse.gui.view.RoundedImageView;
import com.shengyuan.beadhouse.model.CareListBean;

import java.util.List;

/**
 * Created by dell on 2017/11/11.
 */

public class CareListAdapter extends BaseAdapter {
    private List<CareListBean> list;
    /**
     * 当前选中的item  position
     */
    private int curSelectedPosition = 0;

    public CareListAdapter(List<CareListBean> list) {
        this.list = list;
    }

    public void setCurSelectedPosition(int curSelectedPosition) {
        this.curSelectedPosition = curSelectedPosition;
        notifyDataSetChanged();
    }

    public int getCurSelectedPosition() {
        return curSelectedPosition;
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
            convertView = View.inflate(parent.getContext(), R.layout.adapter_care_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.init(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position == list.size() - 1) {
            // 是最后一条数据
            viewHolder.icon.setVisibility(View.INVISIBLE);
            viewHolder.addImg.setVisibility(View.VISIBLE);
            viewHolder.name.setText(parent.getResources().getString(R.string.add_new_old_man));
        } else {
            // 不是最后一条数据
            CareListBean bean = (CareListBean) getItem(position);
            viewHolder.icon.setVisibility(View.VISIBLE);
            viewHolder.addImg.setVisibility(View.INVISIBLE);
            GlideLoader.loadNetWorkResource(parent.getContext(), bean.icon, viewHolder.icon, false);
            viewHolder.name.setText(bean.name);
        }
        if (curSelectedPosition == position) {
            viewHolder.itemLay.setBackgroundResource(R.drawable.shape_corner_select_bg);
            viewHolder.name.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.white));
        } else {
            viewHolder.itemLay.setBackgroundResource(R.drawable.shape_corner_unselect_bg);
            viewHolder.name.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.text_888888));
        }

        return convertView;
    }

    private class ViewHolder {
        LinearLayout itemLay;
//        ImageView icon, addImg;
        RoundedImageView icon;
        ImageView addImg;
        TextView name;

        public void init(View rootView) {
            itemLay = rootView.findViewById(R.id.care_list_item_lay);
            icon = rootView.findViewById(R.id.care_list_item_icon);
            addImg = rootView.findViewById(R.id.care_list_item_add_img);
            name = rootView.findViewById(R.id.care_list_item_name);
        }
    }
}
