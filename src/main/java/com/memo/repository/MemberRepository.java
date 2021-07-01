package com.memo.repository;

import com.memo.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    MemberEntity findByUsername(String username);

    MemberEntity findByCreatedAt(String createdAt);
//    MemberEntity findByColorful_key(String colorful_key);
    MemberEntity findByUsernameAndPassword(String username, String password);
    List<MemberEntity> findAll();
}
