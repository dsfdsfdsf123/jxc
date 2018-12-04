package com.coder520.jxc.entity;

import javax.persistence.*;

/**
 * 菜单实体
 * @author liugang
 * @create 2018/12/5 0:17
 **/
@Entity
@Table(name = "t_menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**菜单名称**/
    @Column(length = 50)
    private String name;

    /**菜单地址**/
    @Column(length = 200)
    private String url;

    /**菜单节点类型1根节点，0叶子节点**/
    private Integer state;

    /**图标样式**/
    @Column(length = 100)
    private String icon;

    /**父节点ID**/
    private Integer pId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }
}
