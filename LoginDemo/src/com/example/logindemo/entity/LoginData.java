package com.example.logindemo.entity;

import java.io.Serializable;

/**
 * 登录数据类
 * 
 * @author yinjy
 */
public class LoginData implements Serializable {

	/** 客户标识 */
	private Long id = null;
	/** 客户状态(1:正常;2:冻结;3:删除) */
	private Short status = null;
	/** 客户名称 */
	private String name = null;
	/** 客户电话 */
	private String phone = null;
	/** 客户头像 */
	private String avatar = null;
	/** 客户性别(0:未知;1:男性;2:女性) */
	private Short gender = null;
	/** 客户生年 */
	private Integer year = null;
	/** 客户身高(厘米) */
	private Integer height = null;
	/** 客户体重(千克) */
	private Double weight = null;
	/** 目标卡路里(千卡) */
	private Double goalCalories = null;
	/** 客户来源(1:宅不住;2:数字健身) */
	private Short source = null;
	/** 登录令牌 */
	private String loginToken = null;
	/** 手环标识 */
	private String bandId = null;
	/** 显示体测 */
	private Boolean showBody = null;
	/** 客户描述 */
	private String description = null;

	/**
	 * 构造函数
	 */
	public LoginData() {
	}

	/**
	 * 构造函数
	 * 
	 * @param id 客户标识
	 * @param status 客户状态(1:正常;2:冻结;3:删除)
	 * @param name 客户名称
	 * @param phone 客户电话
	 * @param avatar 客户头像
	 * @param gender 客户性别(0:未知;1:男性;2:女性)
	 * @param year 客户生年
	 * @param height 客户身高(厘米)
	 * @param weight 客户体重(千克)
	 * @param goalCalories 目标卡路里(千卡)
	 * @param source 客户来源(1:宅不住;2:数字健身)
	 * @param loginToken 登录令牌
	 * @param bandId 手环标识
	 * @param showBody 显示体测
	 * @param description 客户描述
	 */
	public LoginData(Long id, Short status, String name, String phone, String avatar, Short gender,
			Integer year, Integer height, Double weight, Double goalCalories, Short source,
			String loginToken, String bandId, Boolean showBody, String description) {
		this.id = id;
		this.status = status;
		this.name = name;
		this.phone = phone;
		this.avatar = avatar;
		this.gender = gender;
		this.year = year;
		this.height = height;
		this.weight = weight;
		this.goalCalories = goalCalories;
		this.source = source;
		this.loginToken = loginToken;
		this.bandId = bandId;
		this.showBody = showBody;
		this.description = description;
	}

	/**
	 * 获取客户标识
	 * 
	 * @return 客户标识
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置客户标识
	 * 
	 * @param id 客户标识
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取客户状态(1:正常;2:冻结;3:删除)
	 * 
	 * @return 客户状态(1:正常;2:冻结;3:删除)
	 */
	public Short getStatus() {
		return status;
	}

	/**
	 * 设置客户状态(1:正常;2:冻结;3:删除)
	 * 
	 * @param status 客户状态(1:正常;2:冻结;3:删除)
	 */
	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * 获取客户名称
	 * 
	 * @return 客户名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置客户名称
	 * 
	 * @param name 客户名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取客户电话
	 * 
	 * @return 客户电话
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置客户电话
	 * 
	 * @param phone 客户电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取客户头像
	 * 
	 * @return 客户头像
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * 设置客户头像
	 * 
	 * @param avatar 客户头像
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * 获取客户性别(0:未知;1:男性;2:女性)
	 * 
	 * @return 客户性别(0:未知;1:男性;2:女性)
	 */
	public Short getGender() {
		return gender;
	}

	/**
	 * 设置客户性别(0:未知;1:男性;2:女性)
	 * 
	 * @param gender 客户性别(0:未知;1:男性;2:女性)
	 */
	public void setGender(Short gender) {
		this.gender = gender;
	}

	/**
	 * 获取客户生年
	 * 
	 * @return 客户生年
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * 设置客户生年
	 * 
	 * @param year 客户生年
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * 获取客户身高(厘米)
	 * 
	 * @return 客户身高(厘米)
	 */
	public Integer getHeight() {
		return height;
	}

	/**
	 * 设置客户身高(厘米)
	 * 
	 * @param height 客户身高(厘米)
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	/**
	 * 获取客户体重(千克)
	 * 
	 * @return 客户体重(千克)
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * 设置客户体重(千克)
	 * 
	 * @param weight 客户体重(千克)
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/**
	 * 获取目标卡路里(千卡)
	 * 
	 * @return 目标卡路里(千卡)
	 */
	public Double getGoalCalories() {
		return goalCalories;
	}

	/**
	 * 设置目标卡路里(千卡)
	 * 
	 * @param goalCalories 目标卡路里(千卡)
	 */
	public void setGoalCalories(Double goalCalories) {
		this.goalCalories = goalCalories;
	}

	/**
	 * 获取客户来源(1:宅不住;2:数字健身)
	 * 
	 * @return 客户来源(1:宅不住;2:数字健身)
	 */
	public Short getSource() {
		return source;
	}

	/**
	 * 设置客户来源(1:宅不住;2:数字健身)
	 * 
	 * @param source 客户来源(1:宅不住;2:数字健身)
	 */
	public void setSource(Short source) {
		this.source = source;
	}

	/**
	 * 获取登录令牌
	 * 
	 * @return 登录令牌
	 */
	public String getLoginToken() {
		return loginToken;
	}

	/**
	 * 设置登录令牌
	 * 
	 * @param loginToken 登录令牌
	 */
	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}

	/**
	 * 获取手环标识
	 * 
	 * @return 手环标识
	 */
	public String getBandId() {
		return bandId;
	}

	/**
	 * 设置手环标识
	 * 
	 * @param bandId 手环标识
	 */
	public void setBandId(String bandId) {
		this.bandId = bandId;
	}

	/**
	 * 获取显示体测
	 * 
	 * @return 显示体测
	 */
	public Boolean getShowBody() {
		return showBody;
	}

	/**
	 * 设置显示体测
	 * 
	 * @param showBody 显示体测
	 */
	public void setShowBody(Boolean showBody) {
		this.showBody = showBody;
	}

	/**
	 * 获取客户描述
	 * 
	 * @return 客户描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置客户描述
	 * 
	 * @param description 客户描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
