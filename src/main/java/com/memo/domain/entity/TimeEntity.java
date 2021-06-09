package com.memo.domain.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
//다음으로 BoardEntity는 TimeEntity를 상속하고 있는데요.
//TimeEntity는 데이터 조작 시 자동으로 날짜를 수정해주는 JPA의 Auditing 기능을 사용

@Getter
@MappedSuperclass //테이블로 매핑하지 않고, 자식 클래스(엔티티)에게 매핑정보를 상속하기 위한 어노테이션
@EntityListeners(AuditingEntityListener.class) //JPA에게 해당 Entity는 Auditing기능을 사용한다는 것을 알리는 어노테이션
public abstract class TimeEntity {
    @CreatedDate
    //Entity가 처음 저장될때 생성일을 주입하는 어노테이션입니다.
    //이때 생성일은 update할 필요가 없으므로, updatable = false 속성을 추가

    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate //Entity가 수정될때 수정일자를 주입하는 어노테이션입니다.
    private LocalDateTime modifiedDate;
}