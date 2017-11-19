package com.shengyuan.beadhouse.model;

import java.io.Serializable;

/**
 * Created by dell on 2017/11/11.
 */

public class CareListBean implements Serializable {
    public int id;
    public String icon;
    public String name;
    public int age;
    /** 0=男，1=女*/
    public int sex;
    /** 家庭固话*/
    public String familyPhone = "";
    /** 移动电话*/
    public String mobilePhone = "";
    /** 家庭区位*/
    public String addressRang = "";
    /** 家庭住址*/
    public String address = "";
}
