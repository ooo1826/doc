/**
 * �ļ��� ����.java
 * ���� me.xm2017.restful
 * ������ []
 * ����ʱ�䣺 2017��4��24�� ����9:26:08
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

import org.apache.commons.lang.StringUtils;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * Զ��ִ��linux��shell script
 * @author Ickes
 * @since V0.1
 */
public class RemoteExecuteCo{
	// �ַ����Ĭ����utf-8
	private static String DEFAULTCHART="UTF-8";
	private Connection	  conn;
	private String		  ip;
	private String		  userName;
	private String		  userPwd;
	
	public RemoteExecuteCo(String ip,String userName,String userPwd){
		this.ip=ip;
		this.userName=userName;
		this.userPwd=userPwd;
	}
	
	public static void main(String[] args){
		RemoteExecuteCo rec=new RemoteExecuteCo("","root","Fc13656110595");
		// ִ������
		System.out.println(rec.execute("ifconfig").size());
		// ִ�нű�
		rec.execute("ls /data/stockdata");
		// ����������������������ǣ�����ķ���������ִ�гɹ���񶼷��أ�
		// ��������أ����������߽ű�ִ�д��󽫷��ؿ��ַ�
		List<String> strs=rec.executeSuccess("ls /data/stockdata/");
		for(String string:strs){
			System.out.println(string);
		}
		
	}
	
	public RemoteExecuteCo(){
		
	}
	
	/**
	 * Զ�̵�¼linux������
	 * @author Ickes
	 * @since V0.1
	 * @return
	 *         ��¼�ɹ�����true�����򷵻�false
	 */
	public Boolean login(){
		boolean flg=false;
		try{
			conn=new Connection(ip);
			conn.connect();// ����
			flg=conn.authenticateWithPassword(userName,userPwd);// ��֤
		}catch(IOException e){
			e.printStackTrace();
		}
		return flg;
	}
	
	/**
	 * @author Ickes
	 *         Զ��ִ��shll�ű���������
	 * @param cmd
	 *        ����ִ�е�����
	 * @return
	 *         ����ִ����󷵻صĽ��ֵ
	 * @since V0.1
	 */
	public List<String> execute(String cmd){
		List<String> result=null;
		try{
			if(login()){
				Session session=conn.openSession();// ��һ���Ự
				session.execCommand(cmd);// ִ������
				result=processStdout(session.getStdout(),DEFAULTCHART);
				// ���Ϊ�õ���׼���Ϊ�գ�˵���ű�ִ�г�����
				if(result.size()>0){
					result=processStdout(session.getStderr(),DEFAULTCHART);
				}
				conn.close();
				session.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @author Ickes
	 *         Զ��ִ��shll�ű���������
	 * @param cmd
	 *        ����ִ�е�����
	 * @return
	 *         ����ִ�гɹ��󷵻صĽ��ֵ���������ִ��ʧ�ܣ����ؿ��ַ�����null
	 * @since V0.1
	 */
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
	
	/**
	 * �����ű�ִ�з��صĽ��
	 * @author Ickes
	 * @param in
	 *        ����������
	 * @param charset
	 *        ����
	 * @since V0.1
	 * @return
	 *         �Դ��ı��ĸ�ʽ����
	 */
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
	
	public static void setCharset(String charset){
		DEFAULTCHART=charset;
	}
	
	public Connection getConn(){
		return conn;
	}
	
	public void setConn(Connection conn){
		this.conn=conn;
	}
	
	public String getIp(){
		return ip;
	}
	
	public void setIp(String ip){
		this.ip=ip;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setUserName(String userName){
		this.userName=userName;
	}
	
	public String getUserPwd(){
		return userPwd;
	}
	
	public void setUserPwd(String userPwd){
		this.userPwd=userPwd;
	}
}