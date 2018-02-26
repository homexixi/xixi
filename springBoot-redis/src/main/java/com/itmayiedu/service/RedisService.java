package com.itmayiedu.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @classDesc: 功能描述: springboot集成redis
 * @Created by ASUS on 2018/2/22.
 * @createTime: 2018/2/22
 * @version: v1.0
 */
@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setObject(String key, Object value) {
        this.setObject(key, value, null);
    }

    private void setObject(String key, Object value, Long time) {
        if (StringUtils.isEmpty(key) || value == null) {
            return;
        }
        if (value instanceof String) {
            //存放String类型
            String stringValue = (String) value;
            if (time == null) {
                stringRedisTemplate.opsForValue().set(key, stringValue);
            } else {
                stringRedisTemplate.opsForValue().set(key, stringValue, time, TimeUnit.SECONDS);
            }
            return;
        }
        if (value instanceof List) {
            List<String> listValue = (List<String>) value;
            for (String string : listValue) {
                stringRedisTemplate.opsForList().leftPush(key, string);
            }
        }
    }

    public void delKey(String key) {
        stringRedisTemplate.delete(key);
    }

    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public static void main(String[] args) {
        System.out.println("测试");
    }

}
