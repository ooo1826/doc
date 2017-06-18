/**
 * 文件名： s.java
 * 包名： mvc
 * 描述： []
 * 创建时间： 2017年5月9日 下午4:53:57
 * @author dogesoft
 */

package mvc;

import java.util.List;

import org.junit.Test;

import me.predictor.restful.dao.impl.NumbersDaoImpl;
import me.predictor.restful.pojo.StockData;

/**
 * 类名： s
 * 公司名称： []
 * 描述：[]
 * 创建时间： 2017年5月9日 下午4:53:57
 * @author dogesoft
 */
public class MybatisTest{
	
//	@Test
	public void insertTest() throws Exception{
		NumbersDaoImpl impl = new NumbersDaoImpl();
		List<StockData> listObjects = null;
		impl.insertBatch(listObjects ,"me.xm2017.restful.dao.addhis");
	}
	
}
