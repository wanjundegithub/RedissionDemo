//package com.company.controller;
//
//import com.company.biz.UserBiz;
//import lombok.extern.slf4j.Slf4j;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
//@RestController
//@Slf4j
//public class UserController {
//
//    @Resource
//    private RedissonClient redissonClient;
//
//    @Resource
//    private UserBiz userBiz;
//
//    private static final String lock_name="USER";
//
//    @GetMapping("/lock")
//    public String testLock(){
//        //获取锁
//        RLock rLock=redissonClient.getLock(lock_name);
//        //加锁
//        rLock.lock();
//        try {
//            //处理业务逻辑
//            log.info("加锁成功，执行后续代码。线程 ID：" + Thread.currentThread().getId());
//            Thread.sleep(60000);
//        }catch (Exception e){
//            log.error(e.getMessage());
//        }finally {
//            rLock.unlock();
//            // 3.解锁
//            log.info("Finally，释放锁成功。线程 ID：" + Thread.currentThread().getId());
//            return "test lock unlock";
//        }
//    }
//}
