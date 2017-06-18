
package me.predictor.restful.dao.impl;

import org.springframework.stereotype.Repository;

import me.predictor.restful.dao.NumbersDao;
import me.predictor.restful.pojo.StockData;
import me.predictor.restful.utils.MybatisBase;

@Repository("numbersDaoImpl")
public class NumbersDaoImpl extends MybatisBase<StockData> implements NumbersDao{
	
	/**
	 * 创建时间： 2017年4月24日 下午8:51:43
	 * @Fields serialVersionUID : TODO []
	 */
	private static final long serialVersionUID=4828295619423945933L;
	
	/*
	 * (non-Javadoc)
	 * @see me.xm2017.restful.dao.NumbersDao#addHis()
	 */
	@Override
	public int addHis(StockData stockData){
		// TODO Auto-generated method stub
		return this.sqlSessionTemplate.insert("me.xm2017.restful.dao.addhis",stockData);
	}
	
}
