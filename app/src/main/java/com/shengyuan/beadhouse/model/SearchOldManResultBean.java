package com.shengyuan.beadhouse.model;

/**
 * 搜索老人结果bean
 * Created by llj on 2017/12/28.
 */

public class SearchOldManResultBean {

    /**
     * Theolder : {"ID_number":"350303199307132114","address":"1","addtime":"2017-12-21","age":"123","area":"安徽省 淮南市 ","body":"1","cell_phone":"13055260507","fix_phone":"13055260507","is_delete":0,"name":"王某某","photo":"","sex":"女"}
     */

    private TheolderBean Theolder;

    public TheolderBean getTheolder() {
        return Theolder;
    }

    public void setTheolder(TheolderBean Theolder) {
        this.Theolder = Theolder;
    }

    public static class TheolderBean {
        /**
         * ID_number : 350303199307132114
         * address : 1
         * addtime : 2017-12-21
         * age : 123
         * area : 安徽省 淮南市
         * body : 1
         * cell_phone : 13055260507
         * fix_phone : 13055260507
         * is_delete : 0
         * name : 王某某
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
