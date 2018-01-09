package com.shengyuan.beadhouse.model;

/**
 * 登陆成功之后的bean
 * Created by llj on 2017/12/25.
 */

public class LoginBean {
    /**
     * complete : yes
     * focus_count : 2
     * invite_count : 0
     * token : eyJhbGciOiJIUzI1NiIsImV4cCI6MTUxNTQ3MDM4MywiaWF0IjoxNTE1NDY2NzgzfQ.eyJ1c2VybmFtZSI6IjE1MTE4MDQyMDA2In0.63_R4i5YlhLH96bFLHi61qUpt5fS6jAZVBFWmwq6PVc
     * user : {"ID_number":"500234198812146695","datetime":"2017-12-27","name":"滴答滴答","photo":"http://61.155.215.48:5000/data0/storage/pics/15118042006.png","sex":"男","username":"15118042006"}
     */

    private String complete;
    private int focus_count;
    private int invite_count;
    private String token;
    private UserBean user;

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public int getFocus_count() {
        return focus_count;
    }

    public void setFocus_count(int focus_count) {
        this.focus_count = focus_count;
    }

    public int getInvite_count() {
        return invite_count;
    }

    public void setInvite_count(int invite_count) {
        this.invite_count = invite_count;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * ID_number : 500234198812146695
         * datetime : 2017-12-27
         * name : 滴答滴答
         * photo : http://61.155.215.48:5000/data0/storage/pics/15118042006.png
         * sex : 男
         * username : 15118042006
         */

        private String ID_number;
        private String datetime;
        private String name;
        private String photo;
        private String sex;
        private String username;

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
    }
}
