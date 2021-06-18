
package com.memo.Controller;

import com.memo.dto.TestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MemoController {

    private static final Logger logger = LoggerFactory.getLogger(MemoController.class);

    @RequestMapping("/hello")
    public String Hello() {
        return "Hello";
    }


    @RequestMapping(value = "/testVO", method = RequestMethod.POST)
    public ModelAndView test1(HttpServletRequest httpServletRequest, Model model) {
        String username = httpServletRequest.getParameter("name");
        System.out.println("username" + username);

        ModelAndView mav = new ModelAndView();
        mav.addObject("username", username);
        return mav;

    }
/*
    @RequestMapping(value = "/testVO", method = RequestMethod.POST)
    public TestVO test1 () {
        TestVO test = new TestVO();

        String username = test.getUsername();
        String password = test.getPassword();
        test.setUsername(username);
        test.setPassword(password);
        return test;
    }
*/
}

