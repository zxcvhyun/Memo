package com.memo.repository;

import com.memo.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    MemberEntity findByUserid(String userid);

    MemberEntity findByUseridAndPassword(String userid, String password);
    List<MemberEntity> findAll();
}
