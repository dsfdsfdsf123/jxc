package com.coder520.jxc.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * 用户实体
 * @author liugang
 * @create 2018/12/5 0:17
 **/
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "请输入用户名")
    @Column(length = 50)
    private String userName;

    @NotEmpty(message = "请输入密码")
    @Column(length = 50)
    private String password;
    @Column(length = 50)
    private String trueName;
    @Column(length = 1000)
    private String remarks;

    /**
     * 所拥有的角色
     */
    @Transient
    private String roles;

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
