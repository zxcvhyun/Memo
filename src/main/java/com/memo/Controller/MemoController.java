
package com.memo.Controller;

import com.memo.domain.entity.MemberEntity;
import com.memo.domain.entity.MemoEntity;
import com.memo.repository.MemberRepository;
import com.memo.repository.MemoRepository;
import org.json.simple.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class MemoController {
    @Autowired
    MemoRepository memoRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    UserController userController;


    @RequestMapping(method = RequestMethod.GET, path = "/memo/list")
    public List<MemoEntity> getAllMemo() {

        return memoRepository.findAll();
    }

    //데이터 조회
    @RequestMapping(method = RequestMethod.GET, path = "memo/list/{id}")
    public MemoEntity getMemo(@PathVariable String id) {
        Long memoID = Long.parseLong(id);
        Optional<MemoEntity> memo = memoRepository.findById(memoID);
        return memo.get();
    }
    //데이터 수정
    @RequestMapping(method = RequestMethod.POST, path = "/memo/list/{id}")
    public MemoEntity updateMemo(@PathVariable String id, @RequestBody MemoEntity newMemo) {
        Long memoID = Long.parseLong(id);
        Optional<MemoEntity> memo = memoRepository.findById(memoID);

        memo.get().setContents(newMemo.getContents());
        memoRepository.save(memo.get());
        return memo.get();
    }
    //데이터 삽입
    @RequestMapping(method = RequestMethod.POST, path = "/memo/write")
    public JSONObject createMemo(@RequestBody MemoEntity memoEntity) {
        MemberEntity memberEntity = new MemberEntity();
        MemberEntity member = memberRepository.findByUsername(memoEntity.getUsername());

        String colorful_key = BCrypt.hashpw(memberEntity.getCreatedAt(), BCrypt.gensalt());

        JSONObject jsonObject = new JSONObject();
        JSONObject userObject = new JSONObject();

        String username = member.getUsername();
        System.out.println(username);
        SimpleDateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        String sysdate = format1.format(new Date());


        memoEntity.setUsername(username);
        memoEntity.setEntrydate(sysdate);
        jsonObject.put("success", true);
        jsonObject.put("username", memoEntity.getUsername());
        jsonObject.put("contents", memoEntity.getContents());
        jsonObject.put("entrydate", memoEntity.getEntrydate());
        jsonObject.put("colorful_key", colorful_key);

        MemoEntity newMemo = memoRepository.save(memoEntity);




        return jsonObject;
    }

    //데이터 삭제
    @RequestMapping(method = RequestMethod.DELETE, path = "memo/list/{id}")
    public String deleteMemo(@PathVariable String id){
        Long memoID = Long.parseLong(id);
        memoRepository.deleteById(memoID);
        return "delte success";

    }



}

