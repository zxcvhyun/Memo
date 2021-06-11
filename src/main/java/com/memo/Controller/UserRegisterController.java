package com.memo.Controller;

import com.memo.domain.entity.Account;
import com.memo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class UserRegisterController {

    @Autowired
    AccountRepository accountRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/register")
    @ResponseBody
    public String registerUser(@RequestBody Account newAccount) {
        String userid = newAccount.getId();
        String password = newAccount.getPw();

        if (userid.equals("")) return "failed";

        Account account = new Account();
        account.setId(userid);
        account.setPw(password);

        if(accountRepository.findById(userid) != null) return "failed";

        accountRepository.save(account);

        return "success";
    }

}
