package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wu.Kang on 2017/6/22.
 */
@Service
public class UserServiceImpl implements UserService{
    private Map<Integer, User> userMap;

    @Autowired
    private CacheManager cacheManager;

    public UserServiceImpl() {
        userMap = new HashMap<>();
        for(int i = 0; i < 3; ++ i) {
            User user = new User();
            user.setId(i + 1);
            user.setAge(i + 20);
            user.setName("name" + (i + 1));
            user.setSex((byte)(i % 2));
            userMap.put(i + 1, user);
        }
    }

    @Override
    @Cacheable(cacheNames = "users")
    public User getUserById(int id) {
        System.out.println("executing getUserById...");
        return userMap.get(id);
    }

    @Override
    public int addUser(User user) {
        userMap.put(user.getId(), user);
        //cacheManager.getCache("users").put(user.getId(), user);
        return 0;
    }
}
