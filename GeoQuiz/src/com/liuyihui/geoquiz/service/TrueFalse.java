package com.liuyihui.geoquiz.service;

/**
 * 问题答案是或否
 * @author liuyh
 */
public class TrueFalse {
	/** 问题字符串id */
	private int questionStringId;
	/** 问题答案 */
	private boolean trueQuestion;
	
	/**
	 * 构造方法
	 * @param id 问题字符串id
	 * @param trueQuestion 问题答案
	 */
	public TrueFalse(int id ,boolean trueQuestion) {
		this.questionStringId = id;
		this.trueQuestion=trueQuestion;
	}
	
	/**
	 * 获取问题字符串id
	 * @return 
	 */
	public int getQuestionStringId() {
		return questionStringId;
	}
	/**
	 * 设置问题字符串id
	 * @param questionStringId 
	 */
	public void setQuestionStringId(int questionStringId) {
		this.questionStringId = questionStringId;
	}
	/**
	 * 问题答案是否为true
	 * @return 
	 */
	public boolean isTrueQuestion() {
		return trueQuestion;
	}
	/**
	 * 设置问题答案为是or否
	 * @param trueQuestion 
	 */
	public void setTrueQuestion(boolean trueQuestion) {
		this.trueQuestion = trueQuestion;
	}
	
		
	
}
