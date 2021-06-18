package com.memo.Controller;

import com.memo.model.InfoVO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PostController {

    @RequestMapping(method = RequestMethod.POST, path = "/postRequestApi")
    public InfoVO postRequestApi(@RequestBody InfoVO infoVO){ // post 방식은 data를 body에 받아오기 때문에 RequestBody 어노테이션을 사용한다.
        return infoVO;
    }


}