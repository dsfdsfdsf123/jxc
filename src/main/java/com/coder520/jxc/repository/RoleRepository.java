package com.coder520.jxc.repository;

import com.coder520.jxc.entity.Role;
import com.coder520.jxc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 角色repository接口
 * @author liugang
 * @create 2018/12/5 22:17
 **/
public interface RoleRepository extends JpaRepository<Role,Integer> {

    /**
     * 根据用户id查找角色集合
     * @param id 用户id
     * @return 角色集合
     */
    @Query(value = "SELECT r.* FROM t_user u,t_role r,t_user_role ur WHERE u.`id`=ur.`user_id` AND r.`id` = ur.`role_id` AND u.`id` = ?1",nativeQuery = true)
    public List<Role> findRolesByUserId(Integer id);
}
