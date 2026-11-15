package com.north.common.core.page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public final class PageUtils {

    private PageUtils() {
    }

    public static void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        PageHelper.startPage(
                pageDomain.getPageNum(),
                pageDomain.getPageSize()
        ).setReasonable(pageDomain.getReasonable());
    }

    public static TableDataInfo getDataTable(List<?> list) {
        TableDataInfo result = new TableDataInfo();
        result.setCode(200);
        result.setRows(list);
        result.setTotal(new PageInfo<>(list).getTotal());
        return result;
    }

}
