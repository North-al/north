package com.north.system.mapper;

import java.util.List;

public interface SysRoleMapper {
    /**
     * 根据用户 ID 查询有效角色的 role_key。
     */
    List<String> selectRoleKeysByUserId(Long userId);
}
