package com.coder520.jxc.repository;

import com.coder520.jxc.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 菜单repository接口
 * @author liugang
 * @create 2018/12/5 22:17
 **/
public interface MenuRepository extends JpaRepository<Menu,Integer> {

    /**
     * 根据父节点和用户角色id查找子节点
     * @param parentId
     * @param roleId
     * @return
     */
    @Query(value = "SELECT * FROM t_menu WHERE P_id=?1 AND id IN (SELECT menu_id FROM t_role_menu WHERE role_id=?2)",nativeQuery = true)
    public List<Menu> findByParentIdAndRoleId(int parentId, int roleId);
}
