package me.predictor.restful.utils.redis;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.utils.RedisConstant;

public class RedisUtils{
	
	/**
	 * 
	 * 方法名： opsForValue
	 * 描述：[]
	 * 创建时间： 2017年5月16日 下午2:23:17
	 * @param redisTemplate
	 * @param KEY
	 *        yyyyMMdd:sh600000
	 * @param decimal
	 */
	public static void opsForValue(RedisTemplate<String,Object> redisTemplate,final String KEY,BigDecimal decimal){
		redisTemplate.opsForValue().set(RedisConstant.CLOSEPRICE+KEY,decimal,RedisConstant.REDIS_EXPIRY_DATE,TimeUnit.MILLISECONDS);
	}
	
	@SuppressWarnings("all")
	public static void main(String[] args) throws NoSuchMethodException,IllegalArgumentException,InvocationTargetException{
		ClassPathXmlApplicationContext appCtx=new ClassPathXmlApplicationContext("applicationContext-beans.xml");
		final RedisTemplate<String,Object> redisTemplate=appCtx.getBean("redisTemplate",RedisTemplate.class);
		Object obj = null;
		BigDecimal decimal=(BigDecimal)(obj = RedisUtils.getValue(redisTemplate,"123")!=null?obj:null);
	}
	
	/**
	 * 
	 * 方法名： getValue
	 * 描述：[]
	 * 创建时间： 2017年5月16日 下午3:50:39
	 * @param redisTemplate
	 * @param KEY
	 *        yyyyMMdd:sh600000
	 * @return
	 */
	public static Object getValue(RedisTemplate<String,Object> redisTemplate,final String KEY){
		return redisTemplate.opsForValue().get(RedisConstant.CLOSEPRICE+KEY);
	}
}