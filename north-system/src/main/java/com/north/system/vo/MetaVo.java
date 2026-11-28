package com.north.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetaVo {

    private String title;
    private String icon;
    private Boolean noCache;
}