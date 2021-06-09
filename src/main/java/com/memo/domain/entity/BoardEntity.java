package com.memo.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
//DB 테이블과 매핑되는 객체를 정의하는 Entity

//파라미터가 없는 기본 생성자를 추가하는 어노테이션입니다. ( JPA 사용을위해 기본 생성자 생성은 필수 )
@NoArgsConstructor(access = AccessLevel.PROTECTED) //access는 생성자의 접근 권한을 설정하는 속성
@Getter //모든 필드에 getter를 자동생성 해주는 어노테이션입니다.
@Entity //객체를 테이블과 매핑 할 엔티티라고 JPA에게 알려주는 역할을 하는 어노테이션
@Table(name = "board") //엔티티 클래스와 매핑되는 테이블 정보를 명시하는 어노테이션
public class BoardEntity extends TimeEntity {

    @Id //테이블의 기본 키임을 명시
    @GeneratedValue(strategy= GenerationType.IDENTITY) //기본키로 대체키를 사용할 때, 기본키 값 생성 전략을 명시
    private Long id;

    @Column(length = 10, nullable = false) //컬럼을 매핑하는 어노테이션
    private String writer;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    //빌더패턴 클래스를 생성해주는 어노테이션입니다.
    //@Setter 사용 대신 빌더패턴을 사용해야 안정성을 보장할 수 있습니다.
    @Builder
    public BoardEntity(Long id, String title, String content, String writer) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}