package com.company.biz;

import com.company.dao.UserDao;
import com.company.model.User;
import com.company.util.RedissonUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.redisson.codec.JsonJacksonCodec;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserBiz {

    @Resource
    private UserDao userDao;

    @Resource
    private RedissonClient redissonClient;

    private static final String singleKey="SINGLE-KEY";

    private static final String allKey="ALL-KEY";

    public List<User> findAll(){
        //先从缓存中查找，缓存中没有才从数据库查，之后更新缓存
        RList<User> userRList=redissonClient.getList(allKey);
        if(!CollectionUtils.isEmpty(userRList)){
            log.info("从缓存中获取的数据个数:"+userRList.size());
            return userRList;
        }
        List<User> userDaoAll=userDao.findAll();
        if(CollectionUtils.isEmpty(userDaoAll)){
            log.info("查询不到任何数据");
            return null;
        }
        //添加到缓存中,5s后过期
        userRList.addAll(userDaoAll);
        userRList.expire(5000,TimeUnit.MILLISECONDS);
        return userDaoAll;
    }

    public User findById(Integer id){
        RBucket<User> userRBucket =redissonClient.getBucket(singleKey+"-"+id);
        if(userRBucket!=null&&userRBucket.get()!=null){
            log.info("从缓存中查找到的对象:"+userRBucket.get().toString());
            return userRBucket.get();
        }
        User user=userDao.findById(id);
        if(user==null){
            log.info("当前id无法查到数据:"+id);
            return null;
        }
        //添加到缓存中，5s后过期
        userRBucket.set(user,5000, TimeUnit.MILLISECONDS);
        return user;
    }

    public boolean deleteUserById(Integer id){
        //先从数据库中删除，然后删除所有相关缓存
        Integer affectRows=userDao.deleteById(id);
        if(affectRows>0){
            log.info("数据库删除成功，其id:"+id);
        }
        String key=singleKey+"-"+id;
        RedissonUtil.removeCache(redissonClient,key);
        RedissonUtil.removeCache(redissonClient,allKey);
//        RKeys rKeys= redissonClient.getKeys();
//        if(rKeys!=null&&rKeys.count()>0){
//            Iterator<String> keyIterator= rKeys.getKeys().iterator();
//            while (keyIterator.hasNext()){
//                RedissonUtil.removeCache(redissonClient,keyIterator.next());
//            }
//        }
        return true;
    }

    public boolean updateUser(User user){
        //先更新数据库，再删除缓存，最后插入新缓存
        Integer affectRows=userDao.updateUser(user);
        if(affectRows>0){
            log.info("数据库更新成功，其user:"+user.toString());
        }
        String key=singleKey+"-"+user.getId();
        RedissonUtil.removeCache(redissonClient,key);
        RedissonUtil.addCache(redissonClient,key,user, null);
        return true;
    }

    public boolean insertUser(User user){
        Integer affectRows=userDao.insertUser(user);
        if(affectRows>0){
            log.info("数据库插入成功，其user:"+user.toString());
        }
        String key=singleKey+"-"+user.getId();
        RedissonUtil.addCache(redissonClient,key,user, null);
        return true;
    }
}
