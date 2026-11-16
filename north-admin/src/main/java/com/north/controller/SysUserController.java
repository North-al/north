package com.north.controller;


import com.north.common.core.controller.BaseController;
import com.north.common.core.page.TableDataInfo;
import com.north.system.domain.SysUser;
import com.north.system.result.Result;
import com.north.system.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system")
@AllArgsConstructor
public class SysUserController extends BaseController {

    private final UserService userService;

    @GetMapping("/users")
    public TableDataInfo listUsers() {
        startPage();
        return getDataTable(userService.listUsers());

    }


    @PostMapping("/users")
    public Result<Integer> addUser(@RequestBody @Valid SysUser user) {
        int i = userService.addUser(user);
        return Result.success(i);
    }

    @PutMapping("/users")
    public Result<Integer> updateUser(@RequestBody @Valid SysUser user) {
        int i = userService.updateUser(user);
        return Result.success(i, "更新用户信息成功");
    }

    @DeleteMapping("/users/{id}")
    public Result<Integer> delete(@PathVariable Long id) {
        int i = userService.deleteUserById(id);
        return Result.success(i);
    }

    @PostMapping("/users/change/status")
    public Result<Integer> changeUserStatus(@RequestBody SysUser user) {
        int i = userService.changeUserStatus(user.getUserId(), String.valueOf(user.getStatus()));
        return Result.success(i, "修改用户状态成功");
    }
}
