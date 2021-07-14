package com.memo.repository;

import com.memo.domain.entity.MemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface MemoRepository extends JpaRepository<MemoEntity, Long> {
   // 데이터를 가져오거나 조작하는 함수를 정의
   // Interface를 implements하여 미리 만들어진 함수를 사용할 수 있으며, 또한 직접 구현이 가능
   List<MemoEntity> findByUsername(String username);
   MemoEntity findByMemoId(Long memoId);

   @Transactional
   void deleteByMemoIdAndUsername(Long memoId, String username);




   //List<MemoEntity> findByUsernameAndMemoidAnd(String username);
}
