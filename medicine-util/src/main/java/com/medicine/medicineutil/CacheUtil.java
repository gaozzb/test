package com.medicine.medicineutil;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 **/
@Slf4j
@Component
public class CacheUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T getObjectFromRedis(String redisKey, Callable<T> myFunc, int time, TimeUnit timeUnit, TypeReference<T> valueType){
        T objectList = null;
        String value = null;
        try {
            if (redisTemplate == null) {
                //redis配置异常，直接读取表
                objectList = myFunc.call();
                if (objectList == null) {
                    return null;
                }
                log.info(String.format("redis配置异常，直接读取表，缓存key：%s,内容：%s", redisKey, Z.objectToJson(objectList)));
                return objectList;
            }
            if (redisTemplate.hasKey(redisKey)) {
                value = redisTemplate.opsForValue().get(redisKey).toString();
                log.info(String.format("从缓存中获取数据，缓存key：%s,内容：%s", redisKey, value));
                return Z.jsonToObject(valueType, value);
            }
            else{
                objectList = myFunc.call();
                if (objectList == null) {
                    return null;
                }
                setObjectToRedis(redisKey, objectList, time, timeUnit);
            }
        } catch (Exception ex) {
            log.error(String.format("存储缓存失败，缓存key：%s,内容：%s", redisKey, value));
        }
        return objectList;
    }


    public String getObjectStringFromRedis(String redisKey) {
        if (redisTemplate == null) {
            return null;
        }
        if (redisTemplate.hasKey(redisKey)) {
            Object object = redisTemplate.opsForValue().get(redisKey);
            if(object == null){
                return null;
            }
            String value = object.toString();
            log.info(String.format("从缓存中获取数据，缓存key：%s,内容：%s", redisKey, value));
            return value;
        }
        return null;
    }

    public void setObjectToRedis(String redisKey, Object o, long l, TimeUnit timeUnit){
        //加入缓存
        if (redisTemplate == null) {
            return;
        }
        String value = Z.objectToJson(o);
        redisTemplate.opsForValue().set(redisKey, value, l, timeUnit);
        log.info(String.format("加入缓存，缓存key：%s,内容：%s", redisKey, value));
    }




    public void setStringToRedis(String redisKey, String value, long l, TimeUnit timeUnit){
        //加入缓存
        if (redisTemplate == null) {
            log.info(String.format("redisTemplate是null"));
            return;
        }
        redisTemplate.opsForValue().set(redisKey, value, l, timeUnit);
        log.info(String.format("加入缓存，缓存key：%s,内容：%s", redisKey, value));
    }
}
