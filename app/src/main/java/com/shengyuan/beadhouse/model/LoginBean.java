package com.shengyuan.beadhouse.model;

/**
 * 登陆成功之后的bean
 * Created by llj on 2017/12/25.
 */

public class LoginBean {

    /**
     * complete : no
     * token : eyJhbGciOiJIUzI1NiIsImV4cCI6MTUxNDM0NjY5NiwiaWF0IjoxNTE0MzQzMDk2fQ.eyJ1c2VybmFtZSI6IjE1MTE4MDQyMDA2In0.DEhDDhVaWBzXByBdB29qLNbGE4sTOmMRVWhBu2rt-kQ
     * user : {"ID_number":"","datetime":"2017-12-27","name":"","photo":"","sex":"","username":"15118042006"}
     */

    private String complete;
    private String token;
    private UserBean user;

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
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
         * ID_number :
         * datetime : 2017-12-27
         * name :
         * photo :
         * sex :
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
