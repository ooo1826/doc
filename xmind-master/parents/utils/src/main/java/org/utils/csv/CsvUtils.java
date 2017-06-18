
package org.utils.csv;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.Charsets;
import org.apache.log4j.Logger;
import org.utils.ref.ObjectMapUtils;

import com.alibaba.fastjson.JSONArray;
import com.csvreader.CsvReader;

public class CsvUtils{
	
	private static final Logger log=Logger.getLogger(CsvUtils.class);
	
	/**
	 * 
	 * 方法名： readCsv
	 * 描述：[]
	 * 创建时间： 2017年5月10日 下午3:38:14
	 * @param file
	 *        文件路径
	 * @param listObjects
	 *        返回对象集合
	 * @param clazz
	 *        对象.class
	 * @param amount
	 *        读取数量
	 * @throws IOException
	 */
	public static void readCsv(final String file,final List<Object> listObjects,Class<?> clazz,final Long amount) throws IOException{
		CsvReader csvReader=new CsvReader(new FileInputStream(file),Charsets.UTF_8);
		csvReader.readHeaders();
		String headers[]=csvReader.getHeaders();
		Map<String,Object> map=new HashMap<String,Object>();
		long count=0L;
		while(csvReader.readRecord()&&count<amount){
			count++;
			for(String string:headers){
				map.put(string,csvReader.get(string.trim()));
			}
			Object obj=null;
			try{
				obj=ObjectMapUtils.mapToObject(map,clazz);
			}catch(Exception e){
				log.info(JSONArray.toJSONString(map));
			}
			listObjects.add(obj);
		}
	}
	
	/**
	 * 
	 * 方法名： readCsv
	 * 描述：[]
	 * 创建时间： 2017年5月10日 下午3:38:14
	 * @param file
	 *        文件路径
	 * @param listObjects
	 *        返回对象集合
	 * @param clazz
	 *        对象.class
	 */
	public static void readCsv(final String file,final List<Object> listObjects,Class<?> clazz){
		try{
			CsvReader csvReader=new CsvReader(new FileInputStream(file),Charsets.UTF_8);
			csvReader.readHeaders();
			String headers[]=csvReader.getHeaders();
			Map<String,Object> map=new HashMap<String,Object>();
			while(csvReader.readRecord()){
				for(String string:headers){
					map.put(string,csvReader.get(string.trim()));
				}
				Object obj=null;
				try{
					obj=ObjectMapUtils.mapToObject(map,clazz);
				}catch(Exception e){
					log.info(JSONArray.toJSONString(map));
				}
				listObjects.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 方法名： readCsv
	 * 描述：[]
	 * 创建时间： 2017年5月10日 下午6:01:33
	 * @param file
	 * @param listMaps
	 */
	public static void readCsv(final String file,final List<Map<String,Object>> listMaps){
		try{
			CsvReader csvReader=new CsvReader(new FileInputStream(file),Charsets.UTF_8);
			csvReader.readHeaders();
			String headers[]=csvReader.getHeaders();
			while(csvReader.readRecord()){
				Map<String,Object> map=new HashMap<String,Object>();
				for(String string:headers){
					map.put(string,csvReader.get(string.trim()));
				}
				listMaps.add(map);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
