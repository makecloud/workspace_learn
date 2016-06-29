package com.liuyihui.yungeweather.scheduletask.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloJob implements Job {
	
	/** 日志属性 */
	private Logger logger = LoggerFactory.getLogger(HelloJob.class);
	
	/**
	 * 任务方法体
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.debug(this.getClass().getName()+"触发>>>");
	}

}
