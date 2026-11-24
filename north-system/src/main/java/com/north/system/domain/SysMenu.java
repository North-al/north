package com.north.system.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMenu implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long menuId;
    private Long parentId;
    private String menuName;

    /**
     * M：目录，C：菜单，F：按钮/功能权限。
     */
    private String menuType;

    private String path;
    private String component;
    private String perms;
    private String icon;
    private Integer sortOrder;
    private String visible;
    private String status;

    /**
     * Service 组装菜单树时使用
     */
    private List<SysMenu> children = new ArrayList<>();
}
