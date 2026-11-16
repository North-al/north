package com.north.system.service;

import com.north.system.domain.SysUser;

import java.util.List;

public interface UserService {
    List<SysUser> listUsers();

    int addUser(SysUser userAddRequest);

    int updateUser(SysUser user);

    int deleteUserById(Long userId);

    int changeUserStatus(Long userId, String status);

    SysUser selectUserById(Long userId);
}
