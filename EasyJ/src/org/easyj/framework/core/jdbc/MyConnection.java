package org.easyj.framework.core.jdbc;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.easyj.framework.core.proxy.JdbcProxy;
import org.easyj.framework.util.PropertiesUtil;

/**
 * 为连接池提供数据库连接
 * @author 苟伟
 *
 */
public  class MyConnection {
	private static final String JDBC_DRIVER = PropertiesUtil.getPropValue("jdbc.driver");
	private static final String JDBC_URL = PropertiesUtil.getPropValue("jdbc.url");
	private static final String JDBC_NAME = PropertiesUtil.getPropValue("jdbc.name");
	private static final String JDBC_PASS = PropertiesUtil.getPropValue("jdbc.pass");
	
/*	public static long time; //连接使用时间   存放连接空闲时间
	
	public static long getTime() {
		return time;
	}
	public  static void setTime(long time) {
		MyConnection.time = time;
	}*/
	public static MyIC getConnection() throws SQLException{
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		/**动态代理创建connection连接 ***/
		MyIC conn = new JdbcProxy(DriverManager.getConnection(JDBC_URL, JDBC_NAME, JDBC_PASS)).getProxy();
		return conn;
	}
	//关闭连接
	public static void close(MyIC conn){
		ConnectionPoolExcutor.put(conn);
	}

	
}
