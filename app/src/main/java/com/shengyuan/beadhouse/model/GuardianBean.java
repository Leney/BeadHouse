package com.shengyuan.beadhouse.model;

/**
 * 监护人列表实体类
 * Created by dell on 2017/11/12.
 */

public class GuardianBean {

    /**
     * ID_number : 500234198812146695
     * datetime : 2017-12-27
     * name : 滴答滴答
     * photo : http://61.155.215.48:5000/data0/storage/pics/15118042006.png
     * relation : 子女
     * sex : 男
     * username : 15118042006
     * add_time : 2017-12-26
     */

    private String ID_number;
    private String datetime;
    private String name;
    private String photo;
    private String relation;
    private String sex;
    private String username;
    private String add_time;

    public String getID_number() {
        return ID_number;
    }

    public void setID_number(String ID_number) {
        this.ID_number = ID_number;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }
}
