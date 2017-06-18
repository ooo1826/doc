/**
 * 文件名： IdPojo.java
 * 包名： me.xm2017.restful.pojo
 * 描述： []
 * 创建时间： 2017年4月22日 下午6:52:48
 * @author FC
 */

package me.predictor.restful.pojo;

import java.io.Serializable;

/**
 * 类名： IdPojo
 * 公司名称： []
 * 描述：[]
 * 创建时间： 2017年4月22日 下午6:52:48
 * @author FC
 */
public class IdPojo implements Serializable{
	
	/**
	 * 创建时间： 2017年4月22日 下午6:53:04
	 * @Fields serialVersionUID : TODO []
	 */
	private static final long serialVersionUID=3609856966615540477L;
											  
	private Long			  id;

	/**
	 * @return the id
	 */
	public Long getId(){
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id){
		this.id=id;
	}
							  
	
}
