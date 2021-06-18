package com.memo.Controller;

import com.memo.domain.Member;
import com.memo.domain.entity.MemberEntity;
import com.memo.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class MemberController {
   // @Autowired
    MemberRepository memberRepository;

    @PostMapping("/register")
    @ResponseBody
    public String registerUser(@RequestBody MemberEntity newmemberEntity) {
        String userid = newmemberEntity.getId();
        String password = newmemberEntity.getPw();

        if (userid.equals("") || password.equals("")) return "failed";

        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(userid);
        memberEntity.setPw(password);
        //if (memberRepository.findById(userid) != null) return "failed";

        memberRepository.save(memberEntity);
        return "success";
    }
}
/*
    // 메인 페이지
    @GetMapping("/")
    public String index() {
        return "/index";
    }

    // 회원가입 페이지
    @GetMapping("/user/signup")
    public String dispSignup() {
        return "/signup";
    }

     // 로그인 페이지
    @GetMapping("/user/login")
    public String dispLogin() {
        return "/login";
    }
*/


