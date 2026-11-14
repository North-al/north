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
        if (userAddRequest == null) {
            throw new IllegalArgumentException("User add request cannot be null");
        }

        if (userAddRequest.getLoginName() == null || userAddRequest.getLoginName().trim().isEmpty()) {
            throw new IllegalArgumentException("Login name cannot be null or empty");
        }

        return userMapper.insertUser(userAddRequest);
    }
}
