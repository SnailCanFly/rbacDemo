package com.edu.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.user.SysRole;
import com.edu.user.mapper.SysRoleMapper;

@Service
public class SysRoleServiceImpl {
    @Autowired
    private SysRoleMapper roleMapper;

    public SysRole selectById(Integer id){
        return roleMapper.selectById(id);
    }
    public SysRole selectByName(String name) {
        return roleMapper.selectByName(name);
    }
}
