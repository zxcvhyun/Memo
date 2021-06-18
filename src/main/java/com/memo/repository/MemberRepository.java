package com.memo.repository;

import com.memo.domain.Member;
import com.memo.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
 //public MemberEntity findById(String userid);

}
