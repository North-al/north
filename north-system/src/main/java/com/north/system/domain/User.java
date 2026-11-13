package com.north.system.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户领域对象
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    private Long userId;
    private String loginName;
    private String userName;
    private String email;
    private String status;

    /**
     * 暂时作为领域字段保留；不要把它返回给用户列表接口。
     */
    @ToString.Exclude
    private String password;
}