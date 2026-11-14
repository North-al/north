package com.north.system.service;

import com.north.system.domain.User;
import com.north.system.dto.UserAddRequest;

import java.util.List;

public interface UserService {
    List<User> listUsers();

    int addUser(UserAddRequest userAddRequest);
}
