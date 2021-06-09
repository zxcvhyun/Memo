package com.memo.service;

import com.memo.domain.entity.BoardEntity;
import com.memo.dto.BoardDto;
import com.memo.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//  비즈니스 로직을 구현

@AllArgsConstructor //Repository를 주입하기 위해 사용합니다.
@Service //서비스 계층임을 명시
public class BoardService {
    private BoardRepository boardRepository;

    //선언적 트랜잭션이라 부르며, 트랜잭션을 적용하는 어노테이션입니다.
    //save() JpaRepository에 정의된 메서드로, DB에 INSERT, UPDATE를 담당합니다.
    //매개변수로는 Entity를 전달합니다.
    @Transactional
    public List<BoardDto> getBoardlist() {
        List<BoardEntity> boardEntities = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for ( BoardEntity boardEntity : boardEntities) {
            BoardDto boardDTO = BoardDto.builder()
                    .id(boardEntity.getId())
                    .title(boardEntity.getTitle())
                    .content(boardEntity.getContent())
                    .writer(boardEntity.getWriter())
                    .createdDate(boardEntity.getCreatedDate())
                    .build();

            boardDtoList.add(boardDTO);
        }

        return boardDtoList;
    }
    @Transactional
    public BoardDto getPost(Long id) {
        //findById()
        //PK 값을 where 조건으로 하여, 데이터를 가져오기 위한 메서드이며, JpaRepository 인터페이스에서 정의되어 있습니다.
        //반환 값은 Optional 타입인데, 엔티티를 쏙 빼오려면 boardEntityWrapper.get(); 이렇게 get() 메서드를 사용
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityWrapper.get();

        BoardDto boardDTO = BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .writer(boardEntity.getWriter())
                .createdDate(boardEntity.getCreatedDate())
                .build();

        return boardDTO;
    }

    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }
    @Transactional
    public void deletePost(Long id) {
        //deleteById()
        //PK 값을 where 조건으로 하여, 데이터를 삭제하기 위한 메서드이며, JpaRepository 인터페이스에서 정의되어 있습니다.
        boardRepository.deleteById(id);
    }
}