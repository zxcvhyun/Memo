
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
import java.util.ArrayList;
import java.util.Date;
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
    @RequestMapping(method = RequestMethod.POST, path = "/memo/list/user")
    public JSONObject getAllMemo(@RequestBody MemoEntity memoEntity) {
        ArrayList<MemoEntity> memoEntities = new ArrayList<>(memoRepository.findByUsername(memoEntity.getUsername()));
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < memoEntities.size(); i++) {
            JSONObject aJson = new JSONObject();
            jsonObject.put("success", true);
            aJson.put("memoId", memoEntities.get(i).getMemoId());
            aJson.put("username", memoEntities.get(i).getUsername());
            aJson.put("contents", memoEntities.get(i).getContents());
            jsonArray.add(aJson);
        }
        jsonObject.put("memolist", jsonArray);

        return jsonObject;
    }

    // 해당 유저의 해당 메모
    @RequestMapping(method = RequestMethod.POST, path = "/memo/list")
    public JSONObject getMemo(@RequestBody MemoEntity memoEntity) {
       // MemberEntity member = memberRepository.findByUsername(memoEntity.getUsername());
        Long memoId = memoEntity.getMemoId();
        Optional<MemoEntity> memo = memoRepository.findByMemoIdAndUsername(memoId, memoEntity.getUsername());

        JSONObject jsonObject = new JSONObject();

        if (memo.isPresent()) {
            if (memoEntity.getUsername().equals( memo.get().getUsername())) {
                jsonObject.put("success", true);
                jsonObject.put("memoId", memo.get().getMemoId());
                jsonObject.put("username", memo.get().getUsername());
                jsonObject.put("contents", memo.get().getContents());
            }else {
                jsonObject.put("success", false);
            }

        }else {
            jsonObject.put("success", false);

        }
        return jsonObject;
    }

    // 메모 작성
    @RequestMapping(method = RequestMethod.POST, path = "/memo/write")
    public JSONObject createMemo(@RequestBody MemoEntity memoEntity) {
        MemberEntity member = memberRepository.findByUsername(memoEntity.getUsername());
        Optional<MemoEntity> memo = memoRepository.findByMemoId(memoEntity.getMemoId());
        JSONObject jsonObject = new JSONObject();

        Long memoId = memoEntity.getMemoId();
        String username = memoEntity.getUsername();
        String contents = memoEntity.getContents();

        MemoEntity newMemoEntity = new MemoEntity();
        if (member != null) {
            if (memo.isPresent()){
                jsonObject.put("success", false);
            }else {
                newMemoEntity.setMemoId(memoId);
                newMemoEntity.setUsername(username);
                newMemoEntity.setContents(contents);
                System.out.println(username);
                SimpleDateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
                String sysdate = format1.format(new Date());
                newMemoEntity.setEntrydate(sysdate);

                System.out.println(memoId);

                jsonObject.put("success", true);
                jsonObject.put("memoId", memoId);
                jsonObject.put("username", username);
                jsonObject.put("contents", contents);
                jsonObject.put("entrydate", newMemoEntity.getEntrydate());

                memoRepository.save(newMemoEntity);
            }

            }else{
            jsonObject.put("success", false);
        }
        return jsonObject;
    }
    // 메모 수정
    @RequestMapping(method = RequestMethod.POST, path = "/memo/write/edit")
    public JSONObject editMemo(@RequestBody MemoEntity memoEntity) {
        JSONObject jsonObject = new JSONObject();
        Long memoId = memoEntity.getMemoId();
        Optional<MemoEntity> memo = memoRepository.findByMemoId(memoId);

        SimpleDateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        String sysdate = format1.format(new Date());
        String username = memoEntity.getUsername();

        memo.get().setContents(memoEntity.getContents());
        memo.get().setEntrydate(sysdate);

        if (memo.isPresent()) {
            jsonObject.put("success", true);
            jsonObject.put("memoId", memoId);
            jsonObject.put("username", username);
            jsonObject.put("contents", memoEntity.getContents());
            jsonObject.put("entrydate", sysdate);
            memoRepository.save(memo.get());
        }else {
            jsonObject.put("success", false);
        }

        return jsonObject;
    }
    //데이터 삭제
    @RequestMapping(method = RequestMethod.POST, path = "memo/write/delete")
    public JSONObject deleteMemo(@RequestBody MemoEntity memoEntity){
        MemberEntity member = memberRepository.findByUsername(memoEntity.getUsername());
        JSONObject jsonObject = new JSONObject();

        Long memoId = memoEntity.getMemoId();
        String username = memoEntity.getUsername();
      //  String contents = memoEntity.getContents();
        if (member != null) {
            System.out.println("삭제 성공");
            jsonObject.put("success", true);
          //  jsonObject.put("memoId", memoId);
          //  jsonObject.put("username", username);
          //  jsonObject.put("contents", contents);
            memoRepository.deleteByMemoIdAndUsername(memoId, username);
        }  else {
            System.out.println("삭제 실패");
            jsonObject.put("success", false);
        }

        return jsonObject;

    }



}

