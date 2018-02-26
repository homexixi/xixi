package com.itmayiedu.controller;

import com.itmayiedu.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @classDesc: 功能描述:
 * @Created by ASUS on 2018/2/22.
 * @createTime: 2018/2/22
 * @version: v1.0
 */
@RestController
public class RedisController {
    @Autowired
    private RedisService redisService;

    @RequestMapping("/getKey")
    public String getStringKey(String key) {
        String result = redisService.getString(key);
        System.out.println("redis中值："+result);
        return result;
    }

    @RequestMapping("/setKey")
    public String setStringKey(String key, Object object) {
        redisService.setObject(key, object);
        return "success";
    }

    @RequestMapping("/setListKey")
    public String setListKey() {
        ArrayList<String> list = new ArrayList();
        list.add("xixi");
        list.add("niubi");
        list.add("success");
        redisService.setObject("list",list);
        return "success";
    }

}
