package com.north.system.service;

import java.util.List;

public interface SysRoleService {
    List<String> selectRoleKeysByUserId(Long userId);
}
