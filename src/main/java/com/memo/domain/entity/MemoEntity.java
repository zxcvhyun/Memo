package com.memo.domain.entity;

import javax.persistence.*;
import java.util.Objects;
@Entity
public class MemoEntity {
    //DB 테이블과 매핑되는 객체(Entity)를 정의
    //JPA에서는 Entity를 통해 데이터를 조작함

    @Id
    @GeneratedValue
    private Long id;
    private Long memoid;
    private String username;
    private String contents;
    private String entrydate;
    private String moddate;

    public Long getMemoid() {
        return memoid;
    }

    public void setMemoid(Long memoid) {
        this.memoid = memoid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(String entrydate) {
        this.entrydate = entrydate;
    }

    public String getModdate() {
        return moddate;
    }

    public void setModdate(String moddate) {
        this.moddate = moddate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemoEntity memoEntity = (MemoEntity) o;

        return Objects.equals(id, memoEntity.id )&&
                Objects.equals(contents, memoEntity.contents);
    }


}
