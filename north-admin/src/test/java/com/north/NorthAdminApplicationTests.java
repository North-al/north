package com.north;

import com.north.framework.security.service.SysUserDetailsServiceImpl;
import com.north.system.domain.SysUser;
import com.north.system.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class NorthAdminApplicationTests {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void registeredPasswordMatches() {
        SysUser user = sysUserService.selectUserByLoginName("north");

        assertNotNull(user, "数据库查询不到 north 用户");
        assertNotNull(user.getPassword(), "数据库密码字段为 null");

        assertTrue(
                passwordEncoder.matches("123456", user.getPassword()),
                "数据库中的密码哈希与 123456 不匹配"
        );
    }

    @Test
    void userDetailsPasswordMatches() {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername("north");

        assertTrue(
                passwordEncoder.matches("123456", userDetails.getPassword()),
                "UserDetailsService 返回的密码与 123456 不匹配"
        );
    }


    @Test
    void compareDatabasePasswordWithUserDetailsPassword() {
        SysUser user = sysUserService.selectUserByLoginName("north");
        UserDetails details = userDetailsService.loadUserByUsername("north");

        assertNotNull(user, "SysUserService 没查到 north");
        assertNotNull(user.getPassword(), "SysUser 的密码字段为空");
        assertNotNull(details.getPassword(), "UserDetails 返回的密码字段为空");

        boolean databasePasswordMatches =
                passwordEncoder.matches("123456", user.getPassword());

        boolean detailsPasswordMatches =
                passwordEncoder.matches("123456", details.getPassword());

        boolean samePasswordValue =
                user.getPassword().equals(details.getPassword());

        assertTrue(databasePasswordMatches,
                "SysUserService 查出的数据库密码哈希不匹配 123456");
        assertTrue(samePasswordValue,
                "UserDetails 返回的密码值与 SysUserService 查出的密码值不同");
        assertTrue(detailsPasswordMatches,
                "UserDetails 返回的密码哈希不匹配 123456");
    }

}
