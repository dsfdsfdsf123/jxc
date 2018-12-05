package com.coder520.jxc.service.impl;

import com.coder520.jxc.entity.Role;
import com.coder520.jxc.repository.RoleRepository;
import com.coder520.jxc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色Service的实现类
 * @author liugang
 * @create 2018/12/5 23:10
 **/
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findRolesByUserId(Integer id) {
        return roleRepository.findRolesByUserId(id);
    }

    @Override
    public Role findById(Integer id) {
        Role role = new Role();
        role.setId(id);
        return roleRepository.findById(id).get();
    }
}
