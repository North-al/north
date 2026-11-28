package com.north.system.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RouterVo {

    private String name;
    private String path;
    private Boolean hidden;
    private String component;
    private Boolean alwaysShow;
    private String redirect;
    private MetaVo meta;
    private List<RouterVo> children = new ArrayList<>();
}