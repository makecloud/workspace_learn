package com.example.criminalintent.entity;

import java.util.Date;
import java.util.UUID;

/**
 * 代表不道德行为的实体
 * 
 * @author liuyh 2016年9月19日
 */
public class Crime {

	/** id */
	private UUID id;
	/** 标题 */
	private String title;
	/** 发生日期 */
	private Date date;
	/** 是否已解决 */
	private boolean solved;

	@Override
	public String toString() {
		return title;
	}

	/**
	 * 创建（构造方法）
	 */
	public Crime() {
		id = UUID.randomUUID();
		date = new Date();
	}

	/**
	 * 获取标题
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取id
	 * 
	 * @return
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * 获取日期
	 * 
	 * @return
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * 设置日期
	 * 
	 * @param date
	 *        日期
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * 是否已解决
	 * 
	 * @return
	 */
	public boolean isSolved() {
		return solved;
	}

	/**
	 * 设置是否已解决
	 * 
	 * @param solved
	 */
	public void setSolved(boolean solved) {
		this.solved = solved;
	}

}
