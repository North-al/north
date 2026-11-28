package com.north.system.service;

import com.north.system.domain.SysMenu;
import com.north.system.vo.RouterVo;

import java.util.List;

public interface SysMenuService {

    List<SysMenu> selectMenuTreeByUserId(Long userId);

    List<RouterVo> buildMenus(List<SysMenu> menus);
}
