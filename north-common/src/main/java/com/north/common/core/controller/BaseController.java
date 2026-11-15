package com.north.common.core.controller;

import com.north.common.core.page.PageUtils;
import com.north.common.core.page.TableDataInfo;

import java.util.List;

public abstract class BaseController {

    /** 设置当前请求的分页参数 */
    protected void startPage() {
        PageUtils.startPage();
    }

    /** 将分页查询结果转换为表格响应 */
    protected TableDataInfo getDataTable(List<?> list) {
        return PageUtils.getDataTable(list);
    }
}
