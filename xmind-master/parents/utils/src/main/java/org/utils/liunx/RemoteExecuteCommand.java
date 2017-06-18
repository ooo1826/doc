package org.utils.liunx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.utils.Constant;
import org.utils.PropertiesUtil;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class RemoteExecuteCommand{
	
	private Connection conn;
	
	public Boolean login() throws IOException{
		conn=new Connection(PropertiesUtil.getValue(Constant.REMOTE_IP));
		conn.connect();
		return conn.authenticateWithPassword(PropertiesUtil.getValue(Constant.REMOTE_USERNAME),PropertiesUtil.getValue(Constant.REMOTE_PASSWORD));// 锟斤拷证
	}
	
	/**
	 * 
	 * 方法名： executeSuccess
	 * 描述：[]
	 * 创建时间： 2017年5月9日 下午4:32:34
	 * @param cmd
	 *        命令
	 * @param type
	 *        1、List<String>集合；2、StringBuffer
	 * @return
	 */
	public Object executeSuccess(String cmd,int type){
		Object result=null;
		try{
			if(login()){
				Session session=conn.openSession();
				session.execCommand(cmd);
				result=processStdout(session.getStdout(),Constant.DEFAULTCHART,type);
				conn.close();
				session.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return result;
	}
	
	private Object processStdout(InputStream in,String charset,int type){
		InputStream stdout=new StreamGobbler(in);
		Object obj=null;
		try{
			BufferedReader br=new BufferedReader(new InputStreamReader(stdout,charset));
			if(type==1){
				List<String> lists=new ArrayList<String>();
				String line=null;
				while((line=br.readLine())!=null){
					lists.add(line);
				}
				obj=lists;
			}else if(type==2){
				StringBuffer buffer=new StringBuffer();
				String line=null;
				while((line=br.readLine())!=null){
					buffer.append(line);
				}
				obj=buffer;
			}
			
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		return obj;
	}
}
