package com.example.criminalintent.entity;

import java.util.Date;
import java.util.UUID;

/**
 * ����������Ϊ��ʵ��
 * 
 * @author liuyh 2016��9��19��
 */
public class Crime {

	/** id */
	private UUID id;
	/** ���� */
	private String title;
	/** �������� */
	private Date date;
	/** �Ƿ��ѽ�� */
	private boolean solved;

	@Override
	public String toString() {
		return title;
	}

	/**
	 * ���������췽����
	 */
	public Crime() {
		id = UUID.randomUUID();
		date = new Date();
	}

	/**
	 * ��ȡ����
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * ���ñ���
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * ��ȡid
	 * 
	 * @return
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * ��ȡ����
	 * 
	 * @return
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * ��������
	 * 
	 * @param date
	 *        ����
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * �Ƿ��ѽ��
	 * 
	 * @return
	 */
	public boolean isSolved() {
		return solved;
	}

	/**
	 * �����Ƿ��ѽ��
	 * 
	 * @param solved
	 */
	public void setSolved(boolean solved) {
		this.solved = solved;
	}

}
