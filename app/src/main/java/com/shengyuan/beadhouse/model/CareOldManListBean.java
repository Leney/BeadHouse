package com.shengyuan.beadhouse.model;

import java.util.List;

/**
 * Created by llj on 2017/12/27.
 */

public class CareOldManListBean {

    private List<FocusListBean> focus_list;

    public List<FocusListBean> getFocus_list() {
        return focus_list;
    }

    public void setFocus_list(List<FocusListBean> focus_list) {
        this.focus_list = focus_list;
    }

    public static class FocusListBean {
        /**
         * ID_number : 350303199307132113
         * address : 是的根深蒂固
         * addtime : 2017-12-19
         * age : 25
         * area : 广西区 北海市 银海区
         * body : 沃尔特瑞也让他
         * cell_phone : 13055260507
         * fix_phone : 13055260507
         * is_delete : 0
         * name : 李大爷
         * photo :
         * sex : 女
         */

        private String ID_number;
        private String address;
        private String addtime;
        private String age;
        private String area;
        private String body;
        private String cell_phone;
        private String fix_phone;
        private int is_delete;
        private String name;
        private String photo;
        private String sex;

        public String getID_number() {
            return ID_number;
        }

        public void setID_number(String ID_number) {
            this.ID_number = ID_number;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getCell_phone() {
            return cell_phone;
        }

        public void setCell_phone(String cell_phone) {
            this.cell_phone = cell_phone;
        }

        public String getFix_phone() {
            return fix_phone;
        }

        public void setFix_phone(String fix_phone) {
            this.fix_phone = fix_phone;
        }

        public int getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(int is_delete) {
            this.is_delete = is_delete;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
