
package com.memo.Controller;

import com.memo.domain.entity.MemberEntity;
import com.memo.domain.entity.MemoEntity;
import com.memo.repository.MemberRepository;
import com.memo.repository.MemoRepository;
import org.json.simple.JSONArray;
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

    //POST 요청으로 해서 USERNAME 보내줄시 그 사람의 메모리스트를 전체 보여준다.
    @RequestMapping(method = RequestMethod.POST, path = "/memo/list")
    public List<MemoEntity> getAllMemo(@RequestBody MemoEntity memoEntity) {
        MemoEntity memo = memoRepository.findByUsername(memoEntity.getUsername());
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
         // memo.get().setMemoid(memoEntity.getMemoid());

        Long memoId = memoEntity.getMemoid();
        String username = memoEntity.getUsername();
        String contents = memoEntity.getContents();

        MemoEntity newMemoEntity = new MemoEntity();
        if (member != null) {
                newMemoEntity.setMemoid(memoId);
                newMemoEntity.setUsername(username);
                newMemoEntity.setContents(contents);
                System.out.println(username);
                SimpleDateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
                String sysdate = format1.format(new Date());
                newMemoEntity.setEntrydate(sysdate);

                System.out.println(memoId);

                jsonObject.put("success", true);
                jsonObject.put("id", memoId);
                jsonObject.put("username", username);
                jsonObject.put("contents", contents);
                jsonObject.put("entrydate", newMemoEntity.getEntrydate());
                jsonObject.put("colorful_key", colorful_key);
                memoRepository.save(newMemoEntity);
            }else{
            jsonObject.put("success", false);
        }


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

