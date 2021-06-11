package com.memo.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Account {
    @Id @GeneratedValue
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

    public void setPw(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account =  (Account) o;
        return userid.equals(account.userid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, password);
    }

}
