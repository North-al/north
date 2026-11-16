package com.north.system.service.impl;

import com.north.system.domain.SysUser;
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
    public List<SysUser> listUsers() {
        return userMapper.selectUserList();
    }

    @Override
    public int addUser(SysUser userAddRequest) {


        if (userMapper.countByEmail(userAddRequest.getEmail()) > 0) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (userMapper.countByLoginName(userAddRequest.getLoginName()) > 0) {
            throw new IllegalArgumentException("Login name already exists");
        }

        return userMapper.insertUser(userAddRequest);
    }

    @Override
    public int updateUser(SysUser user) {
        if (user.getUserId() == null || user.getUserId() <= 0) {
            throw new IllegalArgumentException("ID 不合法");
        }

        String email = user.getEmail();
        String loginName = user.getLoginName();

        SysUser existingUser = userMapper.getUserById(user.getUserId());
        if (existingUser == null) {
            throw new IllegalArgumentException("用户不存在");
        }

        // 判断修改的邮箱和登录名是否重复
        if (email != null && !email.equals(existingUser.getEmail())) {
            if (userMapper.countByEmail(email) > 0) {
                throw new IllegalArgumentException("Email already exists");
            }
        }

        if (loginName != null && !loginName.equals(existingUser.getLoginName())) {
            if (userMapper.countByLoginName(loginName) > 0) {
                throw new IllegalArgumentException("Login name already exists");
            }
        }


        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUserById(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("ID 不合法");
        }

        return userMapper.deleteUserById(userId);
    }
}
