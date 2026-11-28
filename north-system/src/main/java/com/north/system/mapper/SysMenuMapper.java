package com.north.system.mapper;

import com.north.system.domain.SysMenu;

import java.util.List;

public interface SysMenuMapper {
    List<SysMenu> selectMenuTreeByUserId(Long userId);
}
