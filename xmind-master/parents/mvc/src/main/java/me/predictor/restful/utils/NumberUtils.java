
package me.predictor.restful.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.data.redis.core.ListOperations;

import me.predictor.restful.pojo.Numbers;


public class NumberUtils{
	
	public static void getNumbers(final ListOperations<String,Object> operations,final Numbers nums,String key) throws InstantiationException,IllegalAccessException,NoSuchFieldException,SecurityException,NoSuchMethodException,IllegalArgumentException,InvocationTargetException{
		int size=Integer.parseInt(operations.size(key).toString());
		List<Object> objs=operations.range(key,0,39);
		if(size>=0){
			BigDecimal num0=(BigDecimal)objs.get(0);
			for(int i=1;i<objs.size();i++){
				String fieldName="setNum"+i;
				Method method=nums.getClass().getMethod(fieldName,BigDecimal.class);
				BigDecimal div=getBig(num0,(BigDecimal)objs.get(i));
				method.invoke(nums,div);
			}
		}
		nums.setDesc(key);
	}
	
	private static BigDecimal getBig(BigDecimal today,BigDecimal big){
		BigDecimal sub=sub(today,big);
		BigDecimal div=div(sub,big);
		return div;
	}
	
	public static BigDecimal sub(BigDecimal today,BigDecimal big){
		return today.subtract(big);
	}
	
	public static BigDecimal div(BigDecimal sub,BigDecimal big){
		return sub.divide(big,4,RoundingMode.HALF_UP);
	}
	
}
