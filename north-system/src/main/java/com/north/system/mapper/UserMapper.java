package com.north.system.mapper;


import com.north.system.domain.User;
import com.north.system.dto.UserAddRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserMapper {
    List<User> selectUserList();

    int insertUser(UserAddRequest user);

    int countByLoginName(String name);

    int countByEmail(String email);
}
