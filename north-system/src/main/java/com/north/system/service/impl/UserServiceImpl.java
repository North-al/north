package com.north.system.service.impl;

import com.north.system.domain.User;
import com.north.system.dto.UserAddRequest;
import com.north.system.mapper.UserMapper;
import com.north.system.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public List<User> listUsers() {
        return userMapper.selectUserList();
    }

    @Override
    public int addUser(UserAddRequest userAddRequest) {


        if(userMapper.countByEmail(userAddRequest.getEmail()) > 0) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (userMapper.countByLoginName(userAddRequest.getLoginName()) > 0) {
            throw new IllegalArgumentException("Login name already exists");
        }

        return userMapper.insertUser(userAddRequest);
    }
}
