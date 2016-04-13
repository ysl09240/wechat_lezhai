package com.slin.weixin.menu;

/**
 * 类名: Button </br>
 * 描述: 菜单项的基类  </br>
 * @author SongLin.Yang
 * @data 2016-04-13 15:38
 */
public class Button {

    private String name;//所有一级菜单、二级菜单都共有一个相同的属性，那就是name

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
