package com.shengyuan.beadhouse.gui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shengyuan.beadhouse.R;
import com.shengyuan.beadhouse.model.CouponBean;

import java.util.List;

/**
 * 优惠券Adapter
 * Created by dell on 2017/11/12.
 */

public class CouponAdapter extends BaseAdapter {
    private List<CouponBean> list;

    public CouponAdapter(List<CouponBean> list) {
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
            convertView = View.inflate(parent.getContext(), R.layout.adapter_coupon_item, null);
            viewHolder = new ViewHolder();
            viewHolder.init(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CouponBean bean = (CouponBean) getItem(position);
        viewHolder.name.setText(bean.getType());
        viewHolder.money.setText(bean.getPrice()+"");
        viewHolder.exchangeDate.setText(parent.getResources().getString(R.string.exchange_date) + bean.getUpdated_time());
        viewHolder.validDate.setText(parent.getResources().getString(R.string.valid_date) + bean.getEnd_time());
        viewHolder.stateImg.setImageResource(bean.getAvailable() == 0 ? R.mipmap.coupon_used : R.mipmap.coupon_unuse);

        return convertView;
    }


    private class ViewHolder {
        public TextView money;
        public TextView name;
        public TextView exchangeDate;
        public TextView validDate;
        public ImageView stateImg;

        public void init(View rootView) {
            money = rootView.findViewById(R.id.coupon_adapter_money);
            name = rootView.findViewById(R.id.coupon_adapter_name);
            exchangeDate = rootView.findViewById(R.id.coupon_adapter_exchange_date);
            validDate = rootView.findViewById(R.id.coupon_adapter_valid_date);
            stateImg = rootView.findViewById(R.id.coupon_adapter_state_img);
        }
    }
}
