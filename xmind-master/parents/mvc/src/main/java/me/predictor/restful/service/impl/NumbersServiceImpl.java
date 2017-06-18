/**
 * 文件名： NumbersServiceImpl.java
 * 包名： me.xm2017.restful.service.impl
 * 描述： []
 * 创建时间： 2017年4月23日 上午12:07:33
 * @author FC
 */

package me.predictor.restful.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.utils.date.DateConst;
import org.utils.date.DateUtils;

import me.predictor.restful.dao.NumbersDao;
import me.predictor.restful.pojo.StockData;
import me.predictor.restful.service.NumbersService;
import me.predictor.restful.utils.redis.RedisUtils;

/**
 * 类名： NumbersServiceImpl
 * 公司名称： []
 * 描述：[]
 * 创建时间： 2017年4月23日 上午12:07:33
 * @author FC
 */
@Repository("numbersServiceImpl")
public class NumbersServiceImpl implements NumbersService{
	
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String,Object> redisTemplate;
	
	@Autowired
	@Qualifier("numbersDaoImpl")
	private NumbersDao					 numbersDao;
	
	
	/*
	 * (non-Javadoc)
	 * @see me.xm2017.restful.service.NumbersService#addHis(me.xm2017.restful.pojo.StockData)
	 */
	@Override
	public int addHis(StockData stockData,Date date){
		if(date!=null){
    		String KEY=DateUtils.dateToStr(date,DateConst.FORMAT03)+":"+stockData.getCode();
    		Object obj = null;
    		BigDecimal decimal=(BigDecimal)(obj = RedisUtils.getValue(redisTemplate,KEY)!=null?obj:null);
    		stockData.setYesterday_close(decimal);
		}
		return numbersDao.addHis(stockData);
	}
	
	/*
	 * (non-Javadoc)
	 * @see me.predictor.restful.service.NumbersService#setex(java.util.List)
	 */
	@Override
	public void setex(List<StockData> stockDatas){
		for(StockData stockData:stockDatas){
			String KEY=DateUtils.dateToStr(stockData.getDate(),DateConst.FORMAT03)+":"+stockData.getCode();
			RedisUtils.opsForValue(redisTemplate,KEY,stockData.getClose());
		}
	}
	
}
