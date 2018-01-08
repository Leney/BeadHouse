package com.shengyuan.beadhouse.model;

/**
 * 日程Bean
 * Created by dell on 2017/11/19.
 */

public class ScheduleBean {
    /**
     * content : 1.sadfsdfsdfg
     2.123rwerfsd
     3.122rqwer
     * date : 2017-01-01
     * end_time : 09:00
     * start_time : 08:00
     */

    private String content;
    private String date;
    private String end_time;
    private String start_time;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }
}
