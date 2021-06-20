package com.memo.Controller;


import com.memo.domain.entity.MemberEntity;
import com.memo.model.InfoVO;
import com.memo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    @Autowired
    MemberRepository memberRepository;
    @RequestMapping(method = RequestMethod.POST, path = "/test")
    // post 방식은 data를 body에 받아오기 때문에 RequestBody 어노테이션을 사용한다.
    public String postRequestApi(@RequestBody MemberEntity memberEntity){

        String userid = memberEntity.getId();
        String password = memberEntity.getPw();
       if (userid.equals("") || password.equals("")) return "failed";

        memberEntity.setId(userid);
        memberEntity.setPw(password);
        if (memberRepository.findById(userid) != null) return "failed";

        memberRepository.save(memberEntity);
        return "success";
    }
}
