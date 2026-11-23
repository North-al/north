package com.north.system.mapper;


import com.north.system.domain.SysUser;

import java.util.List;

public interface UserMapper {
    List<SysUser> selectUserList();

    int insertUser(SysUser user);

    int countByLoginName(String name);

    int countByEmail(String email);

    SysUser getUserById(Long userId);

    int updateUser(SysUser request);

    int deleteUserById(Long userId);

    int changeUserStatus(Long userId, String status);

    SysUser selectUserById(Long userId);

    SysUser getUserByLoginName(String loginName);

    SysUser selectUserByLoginName(String loginName);

    SysUser selectUserByUsername(String username);

    List<String> selectPermissionsByLoginName(String loginName);
}
