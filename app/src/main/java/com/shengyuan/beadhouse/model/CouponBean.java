package com.shengyuan.beadhouse.model;

/**
 * 优惠券bean
 * Created by dell on 2017/11/20.
 */

public class CouponBean {
    /**
     * available : 1
     * count : 20
     * editor : luoluo0
     * end_time : 2018-06-20
     * greater_than : 500
     * number : YRLO00015
     * price : 100
     * start_time : 2017-12-20
     * type : 满500减100
     * updated_time : 2017-12-22
     * username : 15118042006
     */

    private int available;
    private int count;
    private String editor;
    private String end_time;
    private int greater_than;
    private String number;
    private int price;
    private String start_time;
    private String type;
    private String updated_time;
    private String username;

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getGreater_than() {
        return greater_than;
    }

    public void setGreater_than(int greater_than) {
        this.greater_than = greater_than;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
