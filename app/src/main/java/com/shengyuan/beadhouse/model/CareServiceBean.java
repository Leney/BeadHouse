package com.shengyuan.beadhouse.model;

/**
 * 照护服务Bean对象
 * Created by dell on 2017/11/7.
 */

public class CareServiceBean {
    /**
     * "照护套餐"类型
     */
    public static final int TYPE_SERVICE_PACKAGE = 0;
    /**
     * "生理数据"类型
     */
    public static final int TYPE_PHYSIOLOGY_INFO = 1;
    /**
     * "远程监护"类型
     */
    public static final int TYPE_OUTSIDE_MONITOR = 2;
    /**
     * "监护人"类型
     */
    public static final int TYPE_GUARDIAN = 3;

    public int type;
    public String name;
    public String describe;
    public int iconRes;
}
