package com.coder520.jxc.service.impl;

import com.coder520.jxc.entity.Menu;
import com.coder520.jxc.repository.MenuRepository;
import com.coder520.jxc.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liugang
 * @create 2018/12/19 22:33
 **/
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuRepository menuRepository;

    @Override
    public List<Menu> findByParentIdAndRoleId(int parentId, int roleId) {
        return menuRepository.findByParentIdAndRoleId(parentId,roleId);
    }
}
