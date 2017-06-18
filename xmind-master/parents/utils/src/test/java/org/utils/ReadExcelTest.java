/**   
 * 文件名： ReadExcelTest.java
 * 包名： org.utils 
 * 描述： []
 * 创建时间： 2017年5月9日 下午4:55:32 
 * @author dogesoft
 */ 

package org.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.utils.csv.CsvUtils;
import org.utils.excel.ReadExcel;

/** 
 * 类名： ReadExcelTest 
 * 公司名称： []
 * 描述：[]
 * 创建时间： 2017年5月9日 下午4:55:32 
 * @author dogesoft
 */
public class ReadExcelTest{
	
	
	@SuppressWarnings("unchecked")
//	@Test
	public static void readExcelTest() throws IOException {
		InputStream is=new FileInputStream("C:/Users/dogesoft/Desktop/000559.xlsx");
		Map<String,Object> file=(Map<String,Object>)ReadExcel.readExcel(is,"C:/Users/dogesoft/Desktop/000559.xlsx");
		Set<String> sets=file.keySet();
		for(String string:sets){
			List<String[]> lists=(List<String[]>)file.get(string);
			for(String[] strings:lists){
				for(String string2:strings){
					System.out.print(string2+"    ");
				}
				
				System.out.println();
			}
			
		}
		
	}
	
public static void main(String[] args){
	String file="C:/Users/FC/Desktop/20170426.csv";
	List<Map<String,Object>> listMaps = new ArrayList<Map<String,Object>>();
	CsvUtils.readCsv(file,listMaps);
	
	
	for(Map<String,Object> map:listMaps){
		Set s = map.keySet();

			for(Object object:s){
				System.out.println(map.get(object));
			}
	}
}
}
