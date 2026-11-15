package com.north.common.core.page;


import lombok.Data;

@Data
public class PageDomain {

    /** 当前页码，从 1 开始 */
    private Integer pageNum = 1;

    /** 每页记录数 */
    private Integer pageSize = 10;

    /** 页码超出范围时是否自动调整 */
    private Boolean reasonable = true;

}
