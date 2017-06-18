/**
 * 文件名： DateUtils.java
 * 包名： org.utils
 * 描述： []
 * 创建时间： 2017年5月10日 上午8:50:33
 * @author dogesoft
 */

package org.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类名： DateUtils
 * 公司名称： []
 * 描述：[]
 * 创建时间： 2017年5月10日 上午8:50:33
 * @author dogesoft
 */
public class DateUtils{
	
	/**
	 * 
	 * 方法名： strToDate
	 * 描述：[]
	 * 创建时间： 2017年5月10日 上午10:37:03
	 * @param str
	 *        字符串格式
	 * @param dateformat
	 * @return
	 * @throws ParseException
	 */
	public static Date strToDate(String str,final String dateformat){
		SimpleDateFormat format=new SimpleDateFormat(dateformat);
		Date date=null;
		try{
			date=format.parse(str);
		}catch(ParseException e){
			e.printStackTrace();
		}
		return date;
	}
	
	public static String dateToStr(Date date,final String dateformat){
		SimpleDateFormat format=new SimpleDateFormat(dateformat);
		return format.format(date);
	}
	
}
