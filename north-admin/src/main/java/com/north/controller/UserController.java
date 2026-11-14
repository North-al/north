package com.north.controller;


import com.north.system.domain.User;
import com.north.system.dto.UserAddRequest;
import com.north.system.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public List<User> listUsers() {
        return userService.listUsers();
    }


    @PostMapping("/users")
    public int post(@RequestBody UserAddRequest user) {
        return userService.addUser(user);
    }
}
