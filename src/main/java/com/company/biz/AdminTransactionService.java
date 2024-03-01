package com.company.biz;

import com.company.dao.UserDao;
import com.company.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AdminTransactionService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserTransactionService userTransactionService;

//    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void addAdminUser(User user){
        User userAdmin=new User();
        User userNormal=new User();
        BeanUtils.copyProperties(user,userAdmin);
        BeanUtils.copyProperties(user,userNormal);
        userAdmin.setUserName("admin");
        userNormal.setUserName("normalUser");

        //userTransactionService.addUser(userNormal);
        addUser(userNormal);


    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void addUser(User user){
            try{
                //userDao.insertUser(user);
                int a=1/0;
            }catch (Exception e){

            }


    }
}
