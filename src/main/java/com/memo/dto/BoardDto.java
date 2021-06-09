package com.memo.dto;

import com.memo.domain.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;
//마지막으로 데이터 전달 객체인 dto

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    //dto에서 필요한 부분을 빌더패턴을 통해 entity로 만듭니다.
    //필요한 Entity는 이런식으로 추가하면 됩니다.
    public BoardEntity toEntity(){
        BoardEntity boardEntity = BoardEntity.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .build();
        return boardEntity;
    }

    @Builder
    public BoardDto(Long id, String title, String content, String writer, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}