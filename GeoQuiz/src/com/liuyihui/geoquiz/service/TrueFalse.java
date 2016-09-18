package com.liuyihui.geoquiz.service;

/**
 * ������ǻ��
 * @author liuyh
 */
public class TrueFalse {
	/** �����ַ���id */
	private int questionStringId;
	/** ����� */
	private boolean trueQuestion;
	
	/**
	 * ���췽��
	 * @param id �����ַ���id
	 * @param trueQuestion �����
	 */
	public TrueFalse(int id ,boolean trueQuestion) {
		this.questionStringId = id;
		this.trueQuestion=trueQuestion;
	}
	
	/**
	 * ��ȡ�����ַ���id
	 * @return 
	 */
	public int getQuestionStringId() {
		return questionStringId;
	}
	/**
	 * ���������ַ���id
	 * @param questionStringId 
	 */
	public void setQuestionStringId(int questionStringId) {
		this.questionStringId = questionStringId;
	}
	/**
	 * ������Ƿ�Ϊtrue
	 * @return 
	 */
	public boolean isTrueQuestion() {
		return trueQuestion;
	}
	/**
	 * ���������Ϊ��or��
	 * @param trueQuestion 
	 */
	public void setTrueQuestion(boolean trueQuestion) {
		this.trueQuestion = trueQuestion;
	}
	
		
	
}
