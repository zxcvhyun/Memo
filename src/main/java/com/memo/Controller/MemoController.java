
package com.memo.Controller;

import com.memo.dto.TestVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemoController {
    @RequestMapping("/hello")
    public String Hello() {
        return "Hello";
    }

    @RequestMapping("/testVO")
    public TestVO test1 () {
        TestVO test = new TestVO();
        test.setUsername("지현");
        test.setPassword("1234");

        return test;
    }
}

