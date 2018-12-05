package com.coder520.jxc.repository;

import com.coder520.jxc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author liugang
 * @create 2018/12/5 22:17
 **/
public interface UserRepository extends JpaRepository<User,Integer> {

    /**
     * 根据用户名查找用户实体
     * @param userName
     * @return
     */
    @Query(value = "select * from t_user where user_name=?1",nativeQuery = true)
    public User findByUserName(String userName);
}
