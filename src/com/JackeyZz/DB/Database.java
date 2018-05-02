package com.JackeyZz.DB;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Database {

	public static final String DBDRIVER="org.gjt.mm.mysql.Driver";
	public static final String DBURL="jdbc:mysql://localhost:3306";
	public static final String DBUSER="root";
	public static final String DBPASS="zzj0213";
	
	private Connection conn=null;
	ResultSet rs=null;
	private Statement stmt=null;
	String sql;
	public Database(){
	}
	
	//打开数据库连接
	public void OpenConn(){
		try {
			Class.forName(DBDRIVER);
			conn=(Connection) DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			System.out.println("连接成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//执行sql语句，返回结果集
	public ResultSet executeQuery(String sql){
		stmt=null;
		rs=null;
		try {
			stmt=(Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	//执行sql语句，更新数据库
	public void executeUpdate(String sql){
		stmt=null;
		try {
			stmt=(Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(sql);
			conn.commit();//提交数据更新
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//
	public void closeStmt(){
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//
	public void closeConn(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 转换编码
	 */
	public static String toGBK(String str){
		try{
			if(str==null)
				str="";
			else {
				str=new String(str.getBytes("ISO-8859-1"),"GBK");
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return str;
	}
	public static void main(String[] args) {
		try {
			Database db=new Database();
			//String sql="select SCHEMA_NAME from information_schema.schemata";
			String sql="show databases";
			db.OpenConn();
			ResultSet rs=db.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
