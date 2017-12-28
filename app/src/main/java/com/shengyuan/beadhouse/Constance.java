package com.shengyuan.beadhouse;

/**
 * 常量帮助类
 * Created by llj on 2017/12/27.
 */

public class Constance {
    /*******************************短信验证码类型*********************************/
    /** 注册*/
    public static final String TYPE_REGIST = "regist";
    /** 忘记密码*/
    public static final String TYPE_FORGET = "forget";
    /** 修改手机号*/
    public static final String TYPE_CHANGE_TEL = "telchange";
    /** 短息登陆*/
    public static final String TYPE_LOGIN = "login";


    /*******************************广播相关Action**********************************/
    /** 添加新的关注老人广播*/
    public static final String ACTION_CARE_NEW_OLD_MAN = "com.shengyuan.beadhouse.ACTION_CARE_NEW_OLD_MAN";
    /** 账户信息改变广播*/
    public static final String ACTION_MODIFY_USER_INFO = "com.shengyuan.beadhouse.ACTION_MODIFY_USER_INFO";
}
