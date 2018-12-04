package com.coder520.jxc.entity;

import javax.persistence.*;

/**
 * 角色菜单关联实体
 * @author liugang
 * @create 2018/12/5 0:26
 **/
@Entity
@Table(name = "t_role_menu")
public class RoleMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**菜单**/
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
