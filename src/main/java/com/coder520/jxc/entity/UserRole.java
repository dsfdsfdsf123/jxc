package com.coder520.jxc.entity;

import javax.persistence.*;

/**
 * 用户角色关联实体
 * @author liugang
 * @create 2018/12/5 0:26
 **/
@Entity
@Table(name = "t_user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**用户**/
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**角色**/
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
