/**
 * �ļ��� RemoteExecuteCommand.java
 * ���� me.xm2017.restful.utils.liunx
 * ������ []
 * ����ʱ�䣺 2017��4��24�� ����9:57:39
 * @author dogesoft
 */

package me.predictor.restful.utils.liunx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import me.predictor.restful.utils.PropertiesUtil;

/**
 * ���� RemoteExecuteCommand
 * ��˾��ƣ� []
 * ������[]
 * ����ʱ�䣺 2017��4��24�� ����9:57:39
 * @author dogesoft
 */
public class RemoteExecuteCommand{
	private static final String	DEFAULTCHART   ="UTF-8";
	private static final String	REMOTE_IP	   ="REMOTE_IP";
	private static final String	REMOTE_USERNAME="REMOTE_USERNAME";
	private static final String	REMOTE_PASSWORD="REMOTE_PASSWORD";
	private Connection			conn;
	
	public Boolean login() throws IOException{
		conn=new Connection(PropertiesUtil.getValue(REMOTE_IP));
		conn.connect();// ����
		return conn.authenticateWithPassword(PropertiesUtil.getValue(REMOTE_USERNAME),PropertiesUtil.getValue(REMOTE_PASSWORD));// ��֤
	}
	
	public List<String> executeSuccess(String cmd){
		List<String> result=null;
		try{
			if(login()){
				Session session=conn.openSession();// ��һ���Ự
				session.execCommand(cmd);// ִ������
				result=processStdout(session.getStdout(),DEFAULTCHART);
				conn.close();
				session.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return result;
	}
	
	private List<String> processStdout(InputStream in,String charset){
		InputStream stdout=new StreamGobbler(in);
		List<String> lists=new ArrayList<String>();
		try{
			BufferedReader br=new BufferedReader(new InputStreamReader(stdout,charset));
			String line=null;
			while((line=br.readLine())!=null){
				lists.add(line);
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		return lists;
	}
	public static void main(String[] args){
		List<String> list = new RemoteExecuteCommand().executeSuccess("ls /data/stockdata");
		for(String string:list){
			System.out.println(string);
		}
	}
}
