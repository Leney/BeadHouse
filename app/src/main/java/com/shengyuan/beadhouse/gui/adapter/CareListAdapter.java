package com.shengyuan.beadhouse.gui.adapter;

import android.content.Context;
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
import com.shengyuan.beadhouse.model.CareOldManListBean;
import com.shengyuan.beadhouse.util.DisplayUtils;

import java.util.List;

/**
 *
 * Created by dell on 2017/11/11.
 */

public class CareListAdapter extends BaseAdapter {
    private List<CareOldManListBean.FocusListBean> list;
    /**
     * 当前选中的item  position
     */
    private int curSelectedPosition = 0;

    private LinearLayout.LayoutParams leftParams, rightParams;

    public CareListAdapter(List<CareOldManListBean.FocusListBean> list, Context context) {
        this.list = list;
        this.leftParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        this.leftParams.leftMargin = DisplayUtils.dip2px(context, 25);
        this.rightParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        this.rightParams.rightMargin = DisplayUtils.dip2px(context, 25);
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
            CareOldManListBean.FocusListBean bean = (CareOldManListBean.FocusListBean) getItem(position);
            viewHolder.icon.setVisibility(View.VISIBLE);
            viewHolder.addImg.setVisibility(View.INVISIBLE);
            GlideLoader.loadNetWorkResource(parent.getContext(), bean.getPhoto(), viewHolder.icon,R.mipmap.personal_default_icon,false);
            viewHolder.name.setText(bean.getName());
        }

        if (position % 2 == 0) {
            // 左边的item
            viewHolder.itemLay.setLayoutParams(leftParams);
        } else {
            // 右边的item
            viewHolder.itemLay.setLayoutParams(rightParams);
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
