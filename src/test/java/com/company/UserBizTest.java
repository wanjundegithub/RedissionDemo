package com.company;

import com.company.biz.UserBiz;
import com.company.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class UserBizTest {

    @Resource
    private UserBiz userBiz;

    @Test
    @Rollback()
    public void testFindAll(){
        List<User> allUsers=userBiz.findAll();
        if(CollectionUtils.isEmpty(allUsers)){
            log.info("查询失败");
        }
        allUsers.stream().forEach(t->
                log.info(t.toString()));
    }

    @Test
    @Rollback()
    public void testFindById(){
        Integer id=1;
        User user=userBiz.findById(id);
        if(user==null){
            log.info("查询失败:"+id);
            return;
        }
        log.info(user.toString());
    }

    @Test
    @Rollback(value = false)
    public void testInsertUser(){
        User user=new User();
        user.setUserName("小明");
        user.setLoginName("xiaoming");
        user.setPassword("123456");
        user.setSex("男");
        user.setEmail("2304564274");
        user.setAddress("武汉");
        log.info("插入user结果："+userBiz.insertUser(user));
        log.info("id:"+user.getId());
    }

    @Test
    @Rollback(value = false)
    public void testUpdateUser(){
        User user=new User();
        user.setId(1);
        user.setLoginName("小明");
        log.info("更新User结果:"+userBiz.updateUser(user));
    }

    @Test
    @Rollback(value = false)
    public void testDeleteUser(){
        Integer id=27;
        log.info("删除user结果:"+userBiz.deleteUserById(id));
    }
}
