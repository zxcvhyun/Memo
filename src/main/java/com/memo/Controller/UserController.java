package com.memo.Controller;


import com.memo.domain.entity.MemberEntity;
import com.memo.model.InfoVO;
//import com.memo.repository.MemberRepository;
import com.memo.repository.MemberRepository;
import com.memo.util.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
    @Autowired
    MemberRepository memberRepository;

    @RequestMapping(method = RequestMethod.POST, path = "/test")
    // post 방식은 data를 body에 받아오기 때문에 RequestBody 어노테이션을 사용한다.
    public MemberEntity postRequestApi(@RequestBody MemberEntity memberEntity){

        String userid = memberEntity.getUserid();
        String password = Hashing.hashingPassword(memberEntity.getPassword());

       // System.out.println(userid);

        MemberEntity newmemberEntity = new MemberEntity();

        newmemberEntity.setUserid(userid);
        newmemberEntity.setPassword(password);

        memberRepository.save(memberEntity);

        return memberEntity;


    }

}


