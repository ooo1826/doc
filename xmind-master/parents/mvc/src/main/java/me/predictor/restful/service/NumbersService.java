/**
 * 文件名： NumbersService.java
 * 包名： me.xm2017.restful.service
 * 描述： []
 * 创建时间： 2017年4月23日 上午12:06:27
 * @author FC
 */

package me.predictor.restful.service;

import java.util.Date;
import java.util.List;

import me.predictor.restful.pojo.StockData;

/**
 * 类名： NumbersService
 * 公司名称： []
 * 描述：[]
 * 创建时间： 2017年4月23日 上午12:06:27
 * @author FC
 */
public interface NumbersService{
	
	/**
	 * 
	 * 方法名： addHis
	 * 描述：[]
	 * 创建时间： 2017年5月17日 下午1:29:00 
	 * @param stockData
	 * @param date !=null ==null 两种
	 * @return
	 */
	public int addHis(StockData stockData,Date date);
	
	public void setex(List<StockData> stockDatas);
}
