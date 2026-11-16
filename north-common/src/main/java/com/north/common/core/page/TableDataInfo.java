package com.north.common.core.page;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TableDataInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private long total;

    /** 当前页数据 */
    private List<?> data;

    /** 表格接口状态码 */
    private int code;

    /** 消息内容 */
    private String message;

}
