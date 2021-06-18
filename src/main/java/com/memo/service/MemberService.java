package com.memo.service;

import com.memo.domain.Member;
import com.memo.dto.MemberDto;
import com.memo.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MemberService  {
    private MemberRepository memberRepository;


}