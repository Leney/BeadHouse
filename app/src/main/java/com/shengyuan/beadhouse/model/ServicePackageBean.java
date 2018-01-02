package com.shengyuan.beadhouse.model;

/**
 * 照护套餐Bean
 * Created by dell on 2018/1/2.
 */
public class ServicePackageBean {

    /**
     * available : 1
     * content : 1、适用于骨折（如腰椎、关节脱位等）的居家老人
     2、两周一次护士随访
     3、每周六天、晨、晚间各一次上门服务
     4、电动5功能4体智能护理床
     * editor : lj
     * effective_days : 7
     * end_time : 2017-01-01
     * pack_progress : 100
     * price : 500
     * shelf_time : Tue, 31 Oct 2017 00:00:00 GMT
     * start_time : 2017-12-26
     * title : 骨折A套餐
     * updated_time : Thu, 19 Oct 2017 00:00:00 GMT
     * user_count : 1
     */

    private int available;
    private String content;
    private String editor;
    private int effective_days;
    private String end_time;
    private String pack_progress;
    private int price;
    private String shelf_time;
    private String start_time;
    private String title;
    private String updated_time;
    private int user_count;

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public int getEffective_days() {
        return effective_days;
    }

    public void setEffective_days(int effective_days) {
        this.effective_days = effective_days;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getPack_progress() {
        return pack_progress;
    }

    public void setPack_progress(String pack_progress) {
        this.pack_progress = pack_progress;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getShelf_time() {
        return shelf_time;
    }

    public void setShelf_time(String shelf_time) {
        this.shelf_time = shelf_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }

    public int getUser_count() {
        return user_count;
    }

    public void setUser_count(int user_count) {
        this.user_count = user_count;
    }
}
