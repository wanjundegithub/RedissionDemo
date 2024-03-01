package com.company;

import com.company.biz.AdminTransactionService;
import com.company.biz.UserTransactionService;
import com.company.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class UserTransactionTest {

    @Autowired
    private AdminTransactionService adminTransactionService;

    @Autowired
    private UserTransactionService userTransactionService;

    @Test
    public void testAddAdminUser(){
        User user=new User();
        user.setId(52);
        user.setUserName("小h");
        user.setLoginName("oooo");
        user.setAddress("北京");
        user.setEmail("899");
        user.setSex("male");
        user.setPassword("111");
        userTransactionService.addUser(user);
    }
}
