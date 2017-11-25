package com.shengyuan.beadhouse.gui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.model.ScheduleBean;

import java.util.List;

/**
 * 当天日程Adapter
 * Created by ldf on 17/6/14.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;

    //头部View个数
    private int mHeaderCount = 0;
    //底部View个数
    private int mBottomCount = 0;

    private final LayoutInflater layoutInflater;
    private List<ScheduleBean> list;

    public ScheduleAdapter(Context context, List<ScheduleBean> list) {
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    public int getmBottomCount() {
        return mBottomCount;
    }

    public void setmBottomCount(int mBottomCount) {
        this.mBottomCount = mBottomCount;
    }

    public int getmHeaderCount() {
        return mHeaderCount;
    }

    public void setmHeaderCount(int mHeaderCount) {
        this.mHeaderCount = mHeaderCount;
    }

    //内容长度
    public int getContentItemCount() {
        return list.size();
    }

    //判断当前item是否是HeadView
    public boolean isHeaderView(int position) {
        return mHeaderCount != 0 && position < mHeaderCount;
    }

    //判断当前item是否是FooterView
    public boolean isBottomView(int position) {
        return mBottomCount != 0 && position >= (mHeaderCount + getContentItemCount());
    }

    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
//        int dataItemCount = getContentItemCount();
//        if (mHeaderCount != 0 && position < mHeaderCount) {
        if (isHeaderView(position)) {
            //头部View
            return ITEM_TYPE_HEADER;
//        } else if (mBottomCount != 0 && position >= (mHeaderCount + dataItemCount)) {
        } else if (isBottomView(position)) {
            //底部View
            return ITEM_TYPE_BOTTOM;
        } else {
            //内容View
            return ITEM_TYPE_CONTENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER) {
            return new HeaderViewHolder(layoutInflater.inflate(R.layout.header_tips_lay, parent, false));
        } else if (viewType == mHeaderCount) {
            return new ViewHolder(layoutInflater.inflate(R.layout.adapter_schedule_item, parent, false));
        } else if (viewType == ITEM_TYPE_BOTTOM) {
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {

        } else if (holder instanceof ViewHolder) {
            ScheduleBean bean = list.get(position);
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.name.setText(bean.name);
            viewHolder.time.setText(bean.beginTime + "-" + bean.endTime);
        } else if (holder instanceof BottomViewHolder) {
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, time;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.schedule_adapter_name);
            time = view.findViewById(R.id.schedule_adapter_time);
        }
    }

    //头部 ViewHolder
    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    //底部 ViewHolder
    public static class BottomViewHolder extends RecyclerView.ViewHolder {
        public BottomViewHolder(View itemView) {
            super(itemView);
        }
    }
}
