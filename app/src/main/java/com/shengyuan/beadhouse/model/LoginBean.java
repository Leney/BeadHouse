package com.shengyuan.beadhouse.model;

import java.util.List;

/**
 * 登陆成功之后的bean
 * Created by llj on 2017/12/25.
 */

public class LoginBean {

    /**
     * olders : []
     * token : eyJhbGciOiJIUzI1NiIsImV4cCI6MTUxNDE4OTgwMywiaWF0IjoxNTE0MTg5MjAzfQ.eyJ1c2VybmFtZSI6IjE1MTE4MDQyMDA2In0.mDu2yJ8ZWbXxcDa3zUXe50SAHMn5Hlz1gSSGZjR8Tfc
     * total : 0
     */

    private String token;
    private int total;
    private List<?> olders;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getOlders() {
        return olders;
    }

    public void setOlders(List<?> olders) {
        this.olders = olders;
    }
}
