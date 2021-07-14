package com.memo.Controller;


import com.memo.domain.entity.MemberEntity;
//import com.memo.repository.MemberRepository;
//import com.memo.model.ResponseModel;
import com.memo.repository.MemberRepository;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
    @Autowired
    MemberRepository memberRepository;

    @RequestMapping(method = RequestMethod.POST, path = "/user/register")
    // post 방식은 data를 body에 받아오기 때문에 RequestBody 어노테이션을 사용한다.
      public JSONObject postRegister(@RequestBody MemberEntity memberEntity){

        String username = memberEntity.getUsername();
        String password = BCrypt.hashpw(memberEntity.getPassword(), BCrypt.gensalt());

        MemberEntity newmemberEntity = new MemberEntity();
        newmemberEntity.setUsername(username);
        newmemberEntity.setPassword(password);

        SimpleDateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        String sysdate = format1.format(new Date());
        newmemberEntity.setCreatedAt(sysdate);

        //colorful_key 암호화(Bycrypt)
        String colorful_key = BCrypt.hashpw(newmemberEntity.getCreatedAt(), BCrypt.gensalt());

        JSONObject jsonObject = new JSONObject();
        JSONObject userObject = new JSONObject();

        if(username.equals("")) {
          jsonObject.put("success", false);
        }
        if(memberRepository.findByUsername(username) != null) {
          jsonObject.put("success", false);
        }else{
          jsonObject.put("success", true);
          userObject.put("username", username);
          userObject.put("colorful_key", colorful_key);
          jsonObject.put("user", userObject);

          memberRepository.save(newmemberEntity);
        }
        return jsonObject;

    }

    @RequestMapping(method = RequestMethod.POST, path = "/user/login")
    public JSONObject postLogin(@RequestBody MemberEntity memberEntity){
      JSONObject jsonObject = new JSONObject();
      MemberEntity member = memberRepository.findByUsername(memberEntity.getUsername());
      String colorful_key = BCrypt.hashpw(memberEntity.getCreatedAt(), BCrypt.gensalt());

      if (member != null) {
          if(!BCrypt.checkpw(memberEntity.getPassword(), member.getPassword())){
            jsonObject.put("success", false);
          }else{
            jsonObject.put("success", true);

            JSONObject userObject = new JSONObject();
            userObject.put("username", member.getUsername());
            userObject.put("colorful_key", colorful_key);
            jsonObject.put("user", userObject);
          }
      }else{
        jsonObject.put("success", false);
      }
      return jsonObject;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/user/auth")
    public JSONObject userAuth(@RequestBody MemberEntity memberEntity) {
      // 클라이언트에서 받아온 key값과 해싱한 create_at 값이 일치하는지 확인
      JSONObject jsonObject = new JSONObject();
      MemberEntity member = memberRepository.findByUsername(memberEntity.getUsername());
      //String key = BCrypt.hashpw(member.getCreatedAt(), BCrypt.gensalt());
      String colorful_key = BCrypt.hashpw(memberEntity.getCreatedAt(), BCrypt.gensalt());

      if (member != null) {
        if (BCrypt.checkpw(BCrypt.hashpw(memberEntity.getCreatedAt(), BCrypt.gensalt()), BCrypt.hashpw(member.getCreatedAt(), BCrypt.gensalt()))) {
          System.out.println("키 안맞음");
          jsonObject.put("success", false);
        } else {
          jsonObject.put("success", true);

          JSONObject userObject = new JSONObject();
          userObject.put("username", member.getUsername());
          userObject.put("colorful_key", colorful_key);
          jsonObject.put("user", userObject);
        }
      }else{
        System.out.println("null  임");
        jsonObject.put("success", false);
      }
      return jsonObject;
    }

}


