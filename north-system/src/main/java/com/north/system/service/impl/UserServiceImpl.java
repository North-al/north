package com.north.system.service.impl;

import com.north.system.domain.User;
import com.north.system.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<User> listUsers() {
        User user = new User();
        user.setUserId(1L);
        user.setUserName("管理员");
        user.setLoginName("admin");
        user.setPassword("admin123");
        user.setEmail("admin@example.com");

        User user1 = new User();
        user1.setUserId(2L);
        user1.setUserName("测试用户");
        user1.setLoginName("haha");
        user1.setPassword("test123");
        user1.setEmail("test@example.com");

        return List.of(user, user1);
    }
}
