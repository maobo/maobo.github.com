package com.mb.dao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {
    //连接的容器    通过 ThreadLocal 存放 JDBC Connection，以达到事务控制的能力。
	 // 定义一个用于放置数据库连接的局部线程变量（使每个线程都拥有自己的连接）
	// static 不是共享的吗？
	//参考：http://blog.csdn.net/dandan2zhuzhu/article/details/26497949
    public final static ThreadLocal<Connection> container = new ThreadLocal<Connection>();
    
    //定义c3p0 数据源
    private static DataSource ds = null;
    
    static{
    	//初始化C3P0
    	System.out.println("C3P0 init...");
    	if(ds==null){
    		ds = new ComboPooledDataSource();
    	}
    	System.out.println("C3P0 init finish...");
    }
    
	/**
	 * 获取连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = container.get();
		if (conn == null) {
			conn = ds.getConnection();
			
			container.set(conn);
		}
		if(conn.getAutoCommit()){
			conn.setAutoCommit(false);
		}
		return conn;
	}
	/**
	 * 获取数据源
	 * @return
	 */
	public static DataSource getSource(){
		return ds;
	}

	/**
	 * 开启事务
	 */
	public static void startTransaction() {
		try{
			Connection conn = container.get();
			if(conn==null){
				conn = ds.getConnection();
				container.set(conn);
			}
			conn.setAutoCommit(false);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 提交事务
	 */
	public static void commitTransaction() {
		Connection conn = null;
		try {
			conn = container.get();
			if (conn != null) {
				conn.commit();
			}
		} catch (SQLException e) {
			try {
				//回滚
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			//e.printStackTrace();
		}
	}

	public static void closeConnection() {
		try {
			Connection conn = container.get();
			if(conn!=null){
				conn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			container.remove();
		}
	}
}