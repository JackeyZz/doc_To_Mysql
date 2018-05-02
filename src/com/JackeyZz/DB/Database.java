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
	
	//�����ݿ�����
	public void OpenConn(){
		try {
			Class.forName(DBDRIVER);
			conn=(Connection) DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			System.out.println("���ӳɹ�");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//ִ��sql��䣬���ؽ����
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
	//ִ��sql��䣬�������ݿ�
	public void executeUpdate(String sql){
		stmt=null;
		try {
			stmt=(Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(sql);
			conn.commit();//�ύ���ݸ���
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
	 * ת������
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
