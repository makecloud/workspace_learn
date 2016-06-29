package com.liuyihui.yungeweather.scheduletask.taskmanage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.TimeUnit;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liuyihui.yungeweather.scheduletask.task.GetWeatherLiveDataTask;
import com.liuyihui.yungeweather.scheduletask.task.HelloJob;

/**
 * Quartz简单任务管理类
 * @author liuyh
 */
public class QuartzManager {
	
	/** 日志属性 */
	private static Logger logger = LoggerFactory.getLogger(QuartzManager.class);
	
	
	public static void main(String[] args) {
		try{
			//获取scheduler实例
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			
			//具体任务
			JobDetail jobDetail1 = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();
			JobDetail jobDetail2 = JobBuilder.newJob(GetWeatherLiveDataTask.class).withIdentity("job_getweatherlivedata", "group1").build();
			
			
			//触发时间点
			SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
					.withIntervalInMinutes(1).repeatForever();
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1")
					.startNow().withSchedule(simpleScheduleBuilder).build();
			
			//交由schedule安排触发
			scheduler.scheduleJob(jobDetail2, trigger);
			
			//为观察程序运行，此设置主程序睡眠3分钟才继续往下运行（因下一个步骤是“关闭Scheduler”）
			TimeUnit.HOURS.sleep(4);
			
			//关闭scheduler
			scheduler.shutdown();
			File f = new File("D:\\cloudsong\\云歌时代_comment\\weatherLiveData3.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(f,true));
			bw.write("定时任务执行完成！\n");
			bw.close();
			System.out.println("定时任务执行完成！定时任务执行完成");
			
			
			
			
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
	}
}
