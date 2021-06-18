package com.memo.Controller;
import com.memo.model.InfoVO;
import org.springframework.web.bind.annotation.*;

@RestController // 컨트롤러라고 알려주는 어노테이션
@RequestMapping("/api") // 여기로 들어올 때 사용할 path를 지정하는 어노테이션. 여기 있는 api들을 사용하기 위해 localhost:8080/api로 들어와야 한다.
@CrossOrigin
public class GetController {
    @RequestMapping(method = RequestMethod.GET, path = "/getRequestApi") // localhost:8080/api/getRequestApi 로 들어오면 해당 getMethod api를 사용할 수 있다.
    public String getRequestApi(){
        return "getRequestApi";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/anotherGetApi")
    public String anotherGetApi(@RequestParam String id, @RequestParam String name){
        return "id="+id +", 이름은 "+name ;
    }
    // ... 아래 코드 추가
    @RequestMapping(method = RequestMethod.GET, path = "/usedModelGetApi")
    public InfoVO usedModelGetApi(InfoVO infoVO){
        return infoVO; // json으로 결과 출력
    }
}