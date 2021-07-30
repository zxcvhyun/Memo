package com.memo.domain.entity;

import com.memo.repository.MemberRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
public class MemberEntity {
    @Id @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String createdAt;
    private String colorful_key;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedAt() {

        return createdAt;
    }

    public void setCreatedAt(String createdAt) {

        this.createdAt = createdAt;

    }

    public String getColorful_key(){return colorful_key;}
    public void setColorful_key(String colorful_key){this.colorful_key = colorful_key;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberEntity memberEntity = (MemberEntity) o;

        return username.equals(memberEntity.username) ;
    }

//    @Override
//    public int hashCode() {
//       // return Objects.hash(id, username, password, createdAt);
//        return Objects.hash(password, createdAt);
//    }



}
