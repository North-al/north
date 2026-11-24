package com.north.system.service.impl;

import com.north.system.mapper.SysRoleMapper;
import com.north.system.service.SysRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleMapper sysRoleMapper;


    @Override
    public List<String> selectRoleKeysByUserId(Long userId) {
        return sysRoleMapper.selectRoleKeysByUserId(userId);
    }
}
