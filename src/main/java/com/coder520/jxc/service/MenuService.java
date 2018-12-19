package com.coder520.jxc.service;

import com.coder520.jxc.entity.Menu;
import com.coder520.jxc.entity.Role;

import java.util.List;

/**
 * 角色Service接口
 * @author liugang
 * @create 2018/12/5 23:07
 **/
public interface MenuService {

    /**
     * 根据父节点和用户角色id查找子节点
     * @param parentId
     * @param roleId
     * @return
     */
    public List<Menu> findByParentIdAndRoleId(int parentId, int roleId);

}
