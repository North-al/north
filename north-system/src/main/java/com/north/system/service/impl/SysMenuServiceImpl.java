package com.north.system.service.impl;

import com.north.system.domain.SysMenu;
import com.north.system.mapper.SysMenuMapper;
import com.north.system.service.SysMenuService;
import com.north.system.vo.MetaVo;
import com.north.system.vo.RouterVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SysMenuServiceImpl implements SysMenuService {

    private static final long MENU_ROOT_ID = 0L;

    private final SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> selectMenuTreeByUserId(Long userId) {
        List<SysMenu> menus =
                sysMenuMapper.selectMenuTreeByUserId(userId);

        return buildMenuTree(menus, MENU_ROOT_ID);
    }

    @Override
    public List<RouterVo> buildMenus(List<SysMenu> menus) {
        List<RouterVo> routers = new ArrayList<>();

        for (SysMenu menu : menus) {
            RouterVo router = new RouterVo();
            router.setName(getRouteName(menu));
            router.setHidden("1".equals(menu.getVisible()));
            router.setMeta(new MetaVo(
                    menu.getMenuName(),
                    menu.getIcon(),
                    false
            ));

            if ("M".equals(menu.getMenuType())) {
                router.setPath(getDirectoryPath(menu.getPath()));
                router.setComponent("Layout");
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");

                if (menu.getChildren() != null
                        && !menu.getChildren().isEmpty()) {
                    router.setChildren(buildMenus(menu.getChildren()));
                }
            } else {
                router.setPath(menu.getPath());
                router.setComponent(menu.getComponent());
                router.setAlwaysShow(false);
            }

            routers.add(router);
        }

        return routers;
    }

    private List<SysMenu> buildMenuTree(
            List<SysMenu> menus,
            Long parentId) {
        List<SysMenu> result = new ArrayList<>();

        for (SysMenu menu : menus) {
            if (parentId.equals(menu.getParentId())) {
                List<SysMenu> children =
                        buildMenuTree(menus, menu.getMenuId());

                menu.setChildren(children);
                result.add(menu);
            }
        }

        return result;
    }

    private String getRouteName(SysMenu menu) {
        String path = menu.getPath();

        if (path == null || path.isBlank()) {
            return "Route" + menu.getMenuId();
        }

        String name = path.replace("/", "").replace("-", "");
        if (name.isEmpty()) {
            return "Route" + menu.getMenuId();
        }

        return Character.toUpperCase(name.charAt(0))
                + name.substring(1);
    }

    private String getDirectoryPath(String path) {
        if (path == null || path.isBlank()) {
            return "/";
        }

        if (!path.startsWith("/")) {
            return "/" + path;
        }

        return path;
    }
}