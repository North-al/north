```text
loginName + 明文密码
  → AuthenticationManager
  → DaoAuthenticationProvider
  → UserDetailsServiceImpl.loadUserByUsername(loginName)
  → SysUserService.selectUserByLoginName(loginName)
  → SysUser.password 的 BCrypt 哈希
  → PasswordEncoder.matches(明文密码, BCrypt哈希)
  → LoginUser
  → Authenticated Authentication

```