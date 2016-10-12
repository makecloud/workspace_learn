package com.example.logindemo.entity;

/**
 * 我的页面 中 菜单项图标和名称
 * 
 * @author liuyh 2016年10月11日
 */
public class MyMenu {

	/** 菜单图标id */
	private int menuIconId;
	/** 菜单中文名称 */
	private String menuName;

	public MyMenu(int id, String name) {
		this.menuIconId = id;
		this.menuName = name;
	}

	public int getMenuIconId() {
		return menuIconId;
	}

	public void setMenuIconId(int menuIconId) {
		this.menuIconId = menuIconId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

}
