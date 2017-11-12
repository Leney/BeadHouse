package com.shengyuan.beadhouse.gui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.model.OldManAccountDetailBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 老人账户详情Adapter
 * Created by dell on 2017/11/12.
 */

public class OldManAccountDetailAdapter extends BaseAdapter {
    private List<OldManAccountDetailBean> list;
    private OnContinueMoneyListener listener;

    public void setContinueMoneyListener(OnContinueMoneyListener listener) {
        this.listener = listener;
    }

    public OldManAccountDetailAdapter(List<OldManAccountDetailBean> list) {
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
            convertView = View.inflate(parent.getContext(), R.layout.adapter_old_man_account_detail_item, null);
            viewHolder = new ViewHolder();
            viewHolder.init(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        OldManAccountDetailBean bean = (OldManAccountDetailBean) getItem(position);
        viewHolder.name.setText(bean.name);
        viewHolder.describe.setText(bean.describe);
        viewHolder.validTime.setText(parent.getResources().getString(R.string.format_valid_date) + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(bean.validityTime));
        viewHolder.continueMoneyBtn.setTag(bean);
        return convertView;
    }

    private class ViewHolder {
        public TextView name, describe, validTime, continueMoneyBtn;

        public void init(View rootView) {
            name = rootView.findViewById(R.id.adapter_account_detail_name);
            describe = rootView.findViewById(R.id.adapter_account_detail_describe);
            validTime = rootView.findViewById(R.id.adapter_account_detail_valid_time);
            continueMoneyBtn = rootView.findViewById(R.id.adapter_account_detail_continue_money_btn);
            continueMoneyBtn.setOnClickListener(continueOnClickListener);
        }
    }

    /**
     * 续费按钮点击事件
     */
    private View.OnClickListener continueOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (listener == null) return;
            listener.onContinueMoney((OldManAccountDetailBean) v.getTag());
        }
    };

    public interface OnContinueMoneyListener {
        void onContinueMoney(OldManAccountDetailBean bean);
    }
}
