package com.company.biz;

import com.company.dao.UserDao;
import com.company.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserTransactionService {

    @Autowired
    private UserDao userDao;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void addUser(User user){
        user.setLoginName("aaa");
        userDao.updateUser(user);
        testSubtract(user);
    }


    public void testSubtract(User user){
        user.setLoginName("bbb");
        userDao.updateUser(user);
        int a = 1/0;
    }

}
