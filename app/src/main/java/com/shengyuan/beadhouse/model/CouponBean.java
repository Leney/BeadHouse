package com.shengyuan.beadhouse.model;

/**
 * 优惠券bean
 * Created by dell on 2017/11/20.
 */

public class CouponBean {
    public int id;
    public int money;
    public String name;
    /** 兑换日期*/
    public String exchangeDate;
    /** 有效期至*/
    public String validDate;
    /** 是否已经使用,true = 已经使用，false=未使用*/
    public boolean isUsed;
}
