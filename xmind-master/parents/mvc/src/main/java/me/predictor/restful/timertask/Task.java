/**
 * 文件名： Task.java
 * 包名： me.predictor.restful.timertask
 * 描述： []
 * 创建时间： 2017年5月16日 上午11:48:28
 * @author dogesoft
 */

package me.predictor.restful.timertask;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 类名： Task
 * 公司名称： []
 * 描述：[]
 * 创建时间： 2017年5月16日 上午11:48:28
 * @author dogesoft
 */
@Component
@EnableScheduling
public class Task{
	
	/*
	 * 秒（0~59）
	 * 分钟（0~59）
	 * 小时（0~23）
	 * 天（月）（0~31，但是你需要考虑你月的天数）
	 * 月（0~11）
	 * 天（星期）（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）
	 * 年份（1970－2099）
	 */
	
	/**
	 * 
	 * 方法名： readCsv
	 * 描述：[ ]
	 * 创建时间： 2017年5月16日 上午11:57:19
	 */
	@Scheduled(cron="0/5 0-59 12,13,14 * * 2-6")
	public void readCsv(){
		
	}
	
	/**
	 * 
	 * 方法名： openReadCsv
	 * 描述：[]
	 * 创建时间： 2017年5月17日 下午1:31:21
	 */
	@Scheduled(cron="0/30 0-59 12,13,14 * * 2-6")
	public void openReadCsv(){
		
	}
	
	/**
	 * 
	 * 方法名： updateHistoryYesterdayClose
	 * 描述：[]
	 * 创建时间： 2017年5月17日 下午1:31:25
	 */
	@Scheduled(cron="0 0 0 * * 1,7")
	public void updateHistoryYesterdayClose(){
		
	}
}
