package com.memo.repository;

import com.memo.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
    public Optional<MemberEntity> findById(String userid);

}
