package com.north.controller;


import com.north.common.core.controller.BaseController;
import com.north.common.core.page.TableDataInfo;
import com.north.system.domain.SysUser;
import com.north.system.result.Result;
import com.north.system.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@Tag(name = "用户管理", description = "用户查询与维护接口")
@RestController
@RequestMapping("/system")
@AllArgsConstructor
public class SysUserController extends BaseController {

    private final SysUserService userService;

    @PreAuthorize("hasAuthority('system:user:list')")
    @Operation(summary = "分页查询用户")
    @GetMapping("/user")
    public TableDataInfo listUsers() {
        startPage();
        return getDataTable(userService.listUsers());

    }

    @PreAuthorize("hasAuthority('system:user:add')")
    @PostMapping("/user")
    @Operation(summary = "添加用户")
    public Result<Integer> addUser(@RequestBody @Valid SysUser user) {
        int i = userService.addUser(user);
        return Result.success(i);
    }

    @PutMapping("/user")
    @Operation(summary = "更新用户")
    public Result<Integer> updateUser(@RequestBody @Valid SysUser user) {
        int i = userService.updateUser(user);
        return Result.success(i, "更新用户信息成功");
    }

    @DeleteMapping("/user/{id}")
    @Operation(summary = "删除用户")
    public Result<Integer> delete(@PathVariable Long id) {
        int i = userService.deleteUserById(id);
        return Result.success(i);
    }

    @PostMapping("/user/change/status")
    @Operation(summary = "修改用户状态")
    public Result<Integer> changeUserStatus(@RequestBody SysUser user) {
        int i = userService.changeUserStatus(user.getUserId(), String.valueOf(user.getStatus()));
        return Result.success(i, "修改用户状态成功");
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "获取用户信息")
    public Result<SysUser> get(@PathVariable Long id) {
        SysUser user = userService.selectUserById(id);
        return Result.success(user);
    }
}
