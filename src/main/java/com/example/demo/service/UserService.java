package com.example.demo.service;

import com.example.demo.model.User;

/**
 * Created by Wu.Kang on 2017/6/22.
 */
public interface UserService {
    User getUserById(int id);
    int addUser(User user);
}
