package com.mb.dao;
 
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据访问层
 * 
 * @author Lyon
 * 
 */

public  abstract class BaseDao {
	public Connection con;
	private PreparedStatement preStatm;
	private ResultSet rs;
	/*
*/
	/**
	 * 通用参数设置方法
	 * 
	 * @param preStatm
	 * @param param
	 * @throws SQLException
	 */
	private void setParam(PreparedStatement preStatm, Object... param) {
		if (preStatm == null || param == null)
			return;
		for (int i = 0; i < param.length; i++) {
			if(param[i]==null){
				param[i] = "Empty";
			}
			try {
				preStatm.setObject(i + 1, param[i]);
			} catch (SQLException e) {
				System.out.println(param[i]);
				e.printStackTrace();
			}
		}
	}
	
	private final void setParam(PreparedStatement preStatm,boolean isLike,Object...param) throws SQLException{
		//模糊匹配
		if (preStatm == null || param == null)
			return;
		for (int i = 0; i < param.length; i++) {
			if(param[i]==null){
				param[i] = "Empty";
			}
			preStatm.setObject(i + 1, "%"+param[i]+"%");
		}
	}

	/**
	 * 通用查询方法
	 * 
	 * @param sql
	 * @param param
	 * @return
	 */
	public ResultSet query(String sql, Object... param) {
		boolean isLike = sql.toLowerCase().indexOf("like")>0;
		try {
			getConnection();
			preStatm = con.prepareStatement(sql);
			if(isLike){
				this.setParam(preStatm, isLike, param);
			}else{
				this.setParam(preStatm, param);
			}
			rs = preStatm.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    //this.closeResult(rs);
		}
		return null;
	}
	
	/**
	 * 关闭
	 * @param
	 * @param
	 * @param rs
	 */
	public void closeResult(ResultSet rs){
		try{
			if(rs!=null){
				rs.close();
			}
			if(preStatm!=null){
				preStatm.close();
			}
			//关闭连接
			if(con!=null){
			 	C3P0Utils.closeConnection();	
			}
		
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	public synchronized int update(String sql,Object...param) throws SQLException{
		int row = 0;
		try {
			getConnection();
			con.setAutoCommit(true);
			preStatm = con.prepareStatement(sql);
			this.setParam(preStatm, param);
			row = preStatm.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeResult(null);
		}
		return row;
	}

	/*private void getConnection(){
		try {
			con = C3P0Utils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public abstract void  getConnection();

}
