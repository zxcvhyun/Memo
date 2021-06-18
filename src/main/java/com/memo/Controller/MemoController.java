/*
package com.memo.Controller;

import com.memo.model.TestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController

public class MemoController {

    private static final Logger logger = LoggerFactory.getLogger(MemoController.class);

    @RequestMapping("/hello")
    public String Hello() {
        return "Hello";
    }

/*
    @RequestMapping(value = "/testVO", method = RequestMethod.POST)
    @ResponseBody
    public String test1(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getParameter("name");

        return username;

    }

    @RequestMapping(path = "/testVO", method = RequestMethod.POST)
    public TestVO test1 (@RequestBody TestVO testVO) {
        return testVO;
    }

    @PostMapping(value = "/postMapping")
    public TestVO postMapping(@RequestBody TestVO testVO) {
        return testVO;
    }

/*
    @RequestMapping(path = "/testVO", method = RequestMethod.POST)
    @ResponseBody
    public TestVO test1 () {
        TestVO test = new TestVO();

        String username = test.getUsername();
        String password = test.getPassword();
        test.setUsername(username);
        test.setPassword(password);
        return test;
    }


}

        */
