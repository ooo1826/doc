/**
 * 文件名： RemoteExecuteTest.java
 * 包名： org.utils
 * 描述： []
 * 创建时间： 2017年5月9日 下午4:41:29
 * @author dogesoft
 */

package org.utils;

import java.util.List;

import org.junit.Test;
import org.utils.liunx.RemoteExecuteCommand;


/**
 * 类名： RemoteExecuteTest
 * 公司名称： []
 * 描述：[]
 * 创建时间： 2017年5月9日 下午4:41:29
 * @author dogesoft
 */
public class RemoteExecuteTest {
	
	@SuppressWarnings("unchecked")
	@Test(timeout=10000)
	public void executeSuccessTest(){
		List<String> list=(List<String>)new RemoteExecuteCommand().executeSuccess("ls /data/stockdata",1);
		
		
		for(String string:list){
			System.out.println(string);
		}
	}
	
}
