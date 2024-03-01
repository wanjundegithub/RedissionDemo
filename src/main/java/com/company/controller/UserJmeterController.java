package com.company.controller;

import com.company.biz.UserTransactionService;
import com.company.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserJmeterController {

    @Autowired
    private UserTransactionService userTransactionService;

    @PostMapping("/addUser")
    public void testJmeter(@RequestBody User user){
        userTransactionService.addUser(user);
    }
}
