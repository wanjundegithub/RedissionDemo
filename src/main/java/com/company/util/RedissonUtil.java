package com.company.util;

import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RedissonUtil {

    private static final Logger logger= LoggerFactory.getLogger(RedissonUtil.class);

    public static <T> void addCache(RedissonClient redissonClient, String key, List<T> datas,
                                  Long expireTime)  {
        if(CollectionUtils.isEmpty(datas)||null==redissonClient){
            logger.error("传入数据为空或redis客户端为空");
            return;
        }
        RList<T> cache=redissonClient.getList(key);
        cache.addAll(datas);
        if(expireTime!=null){
            //存在则替换，不存在则新增
            cache.expire(expireTime, TimeUnit.MILLISECONDS);
        }
    }

    public static <T> void addCache(RedissonClient redissonClient,String key,T data,Long expireTime){
        if(null==redissonClient||null==data){
            logger.error("传入数据为空或redis客户端为空");
            return;
        }
        RBucket<T> cache=redissonClient.getBucket(key);
        if (expireTime!=null){
            //存在则替换，不存在则新增
            cache.set(data,expireTime,TimeUnit.MILLISECONDS);
            return;
        }
        cache.set(data);
    }

    public static void removeCache(RedissonClient redissonClient,String key){
        if(null==redissonClient){
            logger.error("传入数据为空或redis客户端为空");
            return;
        }
        RBucket<Object> cache=redissonClient.getBucket(key);
        if(!cache.isExists()){
            logger.info("缓存不存在，无法删除");
            return;
        }
        cache.delete();
    }

    public static <T> List<T> getCache(RedissonClient redissonClient,String key){
        RList<T> cache=redissonClient.getList(key);
        if(cache.isExists()){
            return cache;
        }
        return null;
    }

    public static <T> T getSingleCache(RedissonClient redissonClient,String key){
        RBucket<T> cache=redissonClient.getBucket(key);
        if(cache.isExists()){
            return cache.get();
        }
        return null;
    }
}
