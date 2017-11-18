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

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private List<ScheduleBean> list;

    public ScheduleAdapter(Context context, List<ScheduleBean> list) {
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.adapter_schedule_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ScheduleBean bean = list.get(position);
        holder.name.setText(bean.name);
        holder.time.setText(bean.beginTime + "-" + bean.endTime);
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
}
