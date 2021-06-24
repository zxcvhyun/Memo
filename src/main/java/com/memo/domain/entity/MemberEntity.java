package com.memo.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class MemberEntity {
    @Id @GeneratedValue
    private Long id;
    private String userid;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberEntity memberEntity = (MemberEntity) o;

        return userid.equals(memberEntity.userid) && password.equals(memberEntity.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userid, password);
    }



}
