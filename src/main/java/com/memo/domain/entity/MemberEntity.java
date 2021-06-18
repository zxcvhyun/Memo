package com.memo.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user")
@NoArgsConstructor
public class MemberEntity {
    @Id
    @GeneratedValue
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
        MemberEntity memberEntity = (MemberEntity) o;
        return userid.equals(memberEntity.userid) && password.equals(memberEntity.password);
    }
   /*
    @Builder
    public MemberEntity(String userid, String password){
        this.userid = userid;
        this.password = password;
    }
    */


}
