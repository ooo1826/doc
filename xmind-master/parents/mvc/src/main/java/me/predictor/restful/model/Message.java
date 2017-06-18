
package me.predictor.restful.model;

public class Message{
	private int	 fileAmount;
	private long time;
	private String mes;
	
	
	
	/**
	 * @return the mes
	 */
	public String getMes(){
		return mes;
	}

	/**
	 * @param mes the mes to set
	 */
	public void setMes(String mes){
		this.mes=mes;
	}

	/**
	 * @return the fileAmount
	 */
	public int getFileAmount(){
		return fileAmount;
	}
	
	/**
	 * @param fileAmount
	 *        the fileAmount to set
	 */
	public void setFileAmount(int fileAmount){
		this.fileAmount=fileAmount;
	}
	
	/**
	 * @return the time
	 */
	public long getTime(){
		return time;
	}
	
	/**
	 * @param time
	 *        the time to set
	 */
	public void setTime(long time){
		this.time=time;
	}
	
}