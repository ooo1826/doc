package me.predictor.restful.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * <br>
 * 类名： BaseDao
 *
 * <br>
 * 公司名称： 【】
 * <br>
 * 描述：【】
 * <br>
 * 创建时间： 2016年5月31日 上午2:32:05
 * @author FC
 */
public class MybatisBase<T> implements Serializable{
	
	/**
	 * <br>
	 * 创建时间： 2016年5月31日 上午2:32:14
	 * @Fields serialVersionUID : TODO 【】
	 */
	private static final long serialVersionUID=856014541129256429L;
	
	@Autowired
	public SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * @return the sqlSessionTemplate
	 */
	public SqlSessionTemplate getSqlSessionTemplate(){
		return sqlSessionTemplate;
	}
	
	/**
	 * @param sqlSessionTemplate
	 *        the sqlSessionTemplate to set
	 */
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
		this.sqlSessionTemplate=sqlSessionTemplate;
	}
	
	/**
	 * 
	 * 方法名： insertBatch
	 * 描述：[mybatis批量插入]
	 * 创建时间： 2017年5月10日 下午5:45:30 
	 * @param listObjects
	 * @param statement
	 * @throws Exception
	 */
	public void insertBatch(List<T> listObjects,String statement) throws Exception{
		// 新获取一个模式为BATCH，自动提交为false的session
		// 如果自动提交设置为true,将无法控制提交的条数，改为最后统一提交，可能导致内存溢出
		SqlSession session=sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
		try{
			if(null!=listObjects&&listObjects.size()>0){
				int amount=listObjects.size();
				for(int i=0,commitAmount=listObjects.size();i<commitAmount;i++){
					Object obj=listObjects.get(i);
					session.insert(statement,obj);
					if((i>0&&i%1000==0)||i==amount-1){
						session.commit();// 手动每1000个一提交，提交后无法回滚
						session.clearCache();// 清理缓存，防止溢出
					}
				}
			}
		}catch(Exception e){
			session.rollback();// 没有提交的数据可以回滚
		}finally{
			session.close();
		}
	}
	
	
	/**
	 * 
	 * 方法名： insertBatch
	 * 描述：[]
	 * 创建时间： 2017年5月10日 下午6:01:52 
	 * @param listObjects
	 * @param statement
	 * @param batch 为了重载，参数在这里无实际作用
	 * @throws Exception
	 */
	public void insertBatch(List<Map<String,Object>> listObjects,String statement,int batch) throws Exception{
		// 新获取一个模式为BATCH，自动提交为false的session
		// 如果自动提交设置为true,将无法控制提交的条数，改为最后统一提交，可能导致内存溢出
		SqlSession session=sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
		try{
			if(null!=listObjects&&listObjects.size()>0){
				int amount=listObjects.size();
				for(int i=0,commitAmount=listObjects.size();i<commitAmount;i++){
					session.insert(statement,listObjects.get(i));//参数Map
					if((i>0&&i%1000==0)||i==amount-1){
						session.commit();// 手动每1000个一提交，提交后无法回滚
						session.clearCache();// 清理缓存，防止溢出
					}
				}
			}
		}catch(Exception e){
			session.rollback();// 没有提交的数据可以回滚
		}finally{
			session.close();
		}
	}
	
}
