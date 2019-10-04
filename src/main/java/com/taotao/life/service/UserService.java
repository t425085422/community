package com.taotao.life.service;

import com.taotao.life.mapper.UserMapper;
import com.taotao.life.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    public void insertUser(User user1) {
        userMapper.insert(user1);
    }

    public User findUserByToken(String token) {
        return  userMapper.findUserByToken(token);
    }

    public User selectUserByAccountId(String accountId) {
        return  userMapper.selectUserByAccountId(accountId);
    }
}
