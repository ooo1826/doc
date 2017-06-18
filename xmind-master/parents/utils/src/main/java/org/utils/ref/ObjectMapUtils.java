package org.utils.ref;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.utils.date.DateConst;
import org.utils.date.DateUtils;

/**
 * 
 * 类名： ObjectMapUtils
 * 公司名称： []
 * 描述：[map对象互转]
 * 创建时间： 2017年5月10日 上午11:01:26
 * @author dogesoft
 */
public class ObjectMapUtils{
	
	/**
	 * 
	 * 方法名： objectToMap
	 * 描述：[将对象转换为map对象]
	 * 创建时间： 2017年5月10日 上午11:01:18
	 * @param thisObj
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static Map<String,Object> objectToMap(Object thisObj){
		Map<String,Object> map=null;
		try{
			map=new HashMap<String,Object>();
			Class<?> clazz=null;
			clazz=Class.forName(thisObj.getClass().getName());
			Method[] methods=clazz.getMethods();// 获取所有的方法
			for(int i=0;i<methods.length;i++){
				String method=methods[i].getName();// 获取方法名
				if(method.startsWith("get")&&!method.contains("getClass")){// 获取get开始的方法名
					Object value=methods[i].invoke(thisObj);// 获取对应对应get方法的value值
					if(value!=null){
						String key=method.substring(3);// 截取get方法除get意外的字符 如getUserName-->UserName
						key=key.substring(0,1).toLowerCase()+key.substring(1);// 将属性的第一个值转为小写
						map.put(key,value);// 将属性key,value放入对象
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 
	 * 方法名： mapToObject
	 * 描述：[将Map对象通过反射机制转换成Bean对象]
	 * 创建时间： 2017年5月10日 上午11:01:03
	 * @param map
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static Object mapToObject(Map<String,Object> map,Class<?> clazz) throws Exception{
		Object obj=null;
		obj=clazz.newInstance();
		if(map!=null&&map.size()>0){
			for(Map.Entry<String,Object> entry:map.entrySet()){
				String propertyName=entry.getKey();       // 属性名
				Object value=entry.getValue();
				String setMethodName="set"+propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);// 获取属性对应的对象字段
				Field field=getClassField(clazz,propertyName);
				if(field==null){
					continue;
				}
				Class<?> fieldTypeClass=field.getType();// 获取字段类型
				value=convertValType(value,fieldTypeClass);// 根据字段类型进行值的转换
				clazz.getMethod(setMethodName,field.getType()).invoke(obj,value);// 调用对象对应的set方法
			}
		}
		return obj;
	}
	
	/**
	 * 
	 * 方法名： getClassField
	 * 描述：[获取指定字段名称查找在class中的对应的Field对象(包括查找父类)]
	 * 创建时间： 2017年5月10日 上午11:02:12
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	private static Field getClassField(Class<?> clazz,String fieldName){
		if(Object.class.getName().equals(clazz.getName())){ return null; }
		Field[] declaredFields=clazz.getDeclaredFields();
		for(Field field:declaredFields){
			if(field.getName().equals(fieldName)){ return field; }
		}
		
		Class<?> superClass=clazz.getSuperclass();
		if(superClass!=null){// 简单的递归一下
			return getClassField(superClass,fieldName);
		}
		return null;
	}
	
	/**
	 * 
	 * 方法名： convertValType
	 * 描述：[将Object类型的值，转换成bean对象属性里对应的类型值]
	 * 创建时间： 2017年5月10日 上午11:03:04
	 * @param value
	 * @param fieldTypeClass
	 * @return
	 */
	private static Object convertValType(Object value,Class<?> fieldTypeClass){
		if(Long.class.getName().equals(fieldTypeClass.getName())||long.class.getName().equals(fieldTypeClass.getName())){
			return Long.parseLong(value.toString());
		}else if(Integer.class.getName().equals(fieldTypeClass.getName())||int.class.getName().equals(fieldTypeClass.getName())){
			return Integer.parseInt(value.toString());
		}else if(Float.class.getName().equals(fieldTypeClass.getName())||float.class.getName().equals(fieldTypeClass.getName())){
			return Float.parseFloat(value.toString());
		}else if(Double.class.getName().equals(fieldTypeClass.getName())||double.class.getName().equals(fieldTypeClass.getName())){
			return Double.parseDouble(value.toString());
		}else if(BigDecimal.class.getName().equals(fieldTypeClass.getName())){
			return new BigDecimal(value.toString());
		}else if(Date.class.getName().equals(fieldTypeClass.getName())){
			return DateUtils.strToDate(value.toString(),DateConst.FORMAT01);
		}else if(String.class.getName().equals(fieldTypeClass.getName())){
			return value.toString();
		}else{
			throw new ClassCastException();
		}
	}
	
}