package com.memo.domain;

public class Member {
    private String userid;
    private String password;

    public String getId() {
        return userid;
    }

    public void setId(String userid) {
        this.userid = userid;
    }

    public String getPw() {
        return password;
    }

    public void setPW(String password) {
        this.password = password;

    }
}