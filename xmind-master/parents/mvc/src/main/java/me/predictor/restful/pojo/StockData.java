/**
 * 文件名： StockData.java
 * 包名： me.xm2017.restful.pojo
 * 描述： []
 * 创建时间： 2017年4月23日 下午10:05:54
 * @author FC
 */

package me.predictor.restful.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 类名： StockData
 * 公司名称： []
 * 描述：[]
 * 创建时间： 2017年4月23日 下午10:05:54
 * @author FC
 */
public class StockData implements Serializable{
	
	/**
	 * 创建时间： 2017年4月23日 下午10:06:13
	 * @Fields serialVersionUID : TODO []
	 */
	private static final long serialVersionUID=-7437122688795606809L;
	
	private String			  code;
	private Date			  date;
	private BigDecimal		  open;
	private BigDecimal		  high;
	private BigDecimal		  low;
	private BigDecimal		  close;
	
	private BigDecimal		  volume;
	private BigDecimal		  money;
	private BigDecimal		  traded_market_value;
	private BigDecimal		  market_value;
	private BigDecimal		  turnover;
	private BigDecimal		  yesterday_close;
	
	
	
	/**
	 * @return the yesterday_close
	 */
	public BigDecimal getYesterday_close(){
		return yesterday_close;
	}

	/**
	 * @param yesterday_close the yesterday_close to set
	 */
	public void setYesterday_close(BigDecimal yesterday_close){
		this.yesterday_close=yesterday_close;
	}

	/**
	 * @return the volume
	 */
	public BigDecimal getVolume(){
		return volume;
	}
	
	/**
	 * @param volume
	 *        the volume to set
	 */
	public void setVolume(BigDecimal volume){
		this.volume=volume;
	}
	
	/**
	 * @return the money
	 */
	public BigDecimal getMoney(){
		return money;
	}
	
	/**
	 * @param money
	 *        the money to set
	 */
	public void setMoney(BigDecimal money){
		this.money=money;
	}
	
	/**
	 * @return the traded_market_value
	 */
	public BigDecimal getTraded_market_value(){
		return traded_market_value;
	}
	
	/**
	 * @param traded_market_value
	 *        the traded_market_value to set
	 */
	public void setTraded_market_value(BigDecimal traded_market_value){
		this.traded_market_value=traded_market_value;
	}
	
	/**
	 * @return the market_value
	 */
	public BigDecimal getMarket_value(){
		return market_value;
	}
	
	/**
	 * @param market_value
	 *        the market_value to set
	 */
	public void setMarket_value(BigDecimal market_value){
		this.market_value=market_value;
	}
	
	/**
	 * @return the turnover
	 */
	public BigDecimal getTurnover(){
		return turnover;
	}
	
	/**
	 * @param turnover
	 *        the turnover to set
	 */
	public void setTurnover(BigDecimal turnover){
		this.turnover=turnover;
	}
	
	/**
	 * @return the code
	 */
	public String getCode(){
		return code;
	}
	
	/**
	 * @param code
	 *        the code to set
	 */
	public void setCode(String code){
		this.code=code;
	}
	
	/**
	 * @return the date
	 */
	public Date getDate(){
		return date;
	}
	
	/**
	 * @param date
	 *        the date to set
	 */
	public void setDate(Date date){
		this.date=date;
	}
	
	/**
	 * @return the open
	 */
	public BigDecimal getOpen(){
		return open;
	}
	
	/**
	 * @param open
	 *        the open to set
	 */
	public void setOpen(BigDecimal open){
		this.open=open;
	}
	
	/**
	 * @return the high
	 */
	public BigDecimal getHigh(){
		return high;
	}
	
	/**
	 * @param high
	 *        the high to set
	 */
	public void setHigh(BigDecimal high){
		this.high=high;
	}
	
	/**
	 * @return the low
	 */
	public BigDecimal getLow(){
		return low;
	}
	
	/**
	 * @param low
	 *        the low to set
	 */
	public void setLow(BigDecimal low){
		this.low=low;
	}
	
	/**
	 * @return the close
	 */
	public BigDecimal getClose(){
		return close;
	}
	
	/**
	 * @param close
	 *        the close to set
	 */
	public void setClose(BigDecimal close){
		this.close=close;
	}
	
}
