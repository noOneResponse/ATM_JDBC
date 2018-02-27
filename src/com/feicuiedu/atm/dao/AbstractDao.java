package com.feicuiedu.atm.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.feicuiedu.atm.anno.Column;
import com.feicuiedu.atm.anno.Table;
import com.feicuiedu.atm.util.CommonUtils;

public class AbstractDao<T> {
	
	/**
	 * sql插入   用于向数据库新增数据
	 * @param tObj 与数据库表相对应的类的实例
	 * @param objects 需要插入的数据，不定个数
	 * @throws SQLException 
	 */
	public void insertMySql (T tObj,Object ...objects) throws SQLException {
		
		Table table = tObj.getClass().getAnnotation(Table.class);
		StringBuilder sbSql = new StringBuilder();
		sbSql.append("insert into ");
		
		// 表名
		sbSql.append(table.value());
		sbSql.append(" (");
		Field[] fields = tObj.getClass().getDeclaredFields();
		
		for (Field field : fields) {
			
			//设置true可以反射出私有属性
			field.setAccessible(true);
			Column column = field.getAnnotation(Column.class);
			sbSql.append(column.value()).append(",");
		}
		
		sbSql.deleteCharAt(sbSql.length()-1);
		sbSql.append(" ) values ( ");
		
		for (Field field : fields) {
			
			field.setAccessible(true);
			sbSql.append("?, ");
		}
		
		sbSql.deleteCharAt(sbSql.length()-2);
		sbSql.append(" ) ");
		
		//输出设计好的sql语句
		//System.out.println(sbSql.toString());
		
		//下面链接数据库
		Connection con = getMysqlConnection();
		
		//获取执行的sql的PrepareStatement对象
		PreparedStatement preparedStatement = con.prepareStatement(sbSql.toString());
		
		int count = 1;
		
		//给preparedStatement的？赋值
		for (Object value : objects) {
			
			preparedStatement.setObject(count++, value);
		}
		
		//执行sql语句，返回结果——新增
		preparedStatement.executeUpdate();
		
		//关闭资源
		preparedStatement.close();
		con.close();		
	}
	
/**
 * sql查询，用于查询数据库中对应的数据
 * @param tObj	传入的T对象
 * @param whereSql	含有？的where条件
 * @param objects	对？赋值
 * @return 返回符合查询条件的T对象
 * @throws SQLException
 * @throws IllegalArgumentException
 * @throws IllegalAccessException
 * @throws NoSuchFieldException
 * @throws SecurityException
 * @throws InstantiationException
 * @throws InvocationTargetException
 * @throws NoSuchMethodException
 */
	@SuppressWarnings("unchecked")
	public List<T> queryUserMySql(T tObj,Object whereSql,Object ...objects) throws SQLException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InstantiationException, InvocationTargetException, NoSuchMethodException {
		
		Table table = tObj.getClass().getAnnotation(Table.class);
		
		StringBuilder sbSql = new StringBuilder();
		
		sbSql.append(" select ");
		
		// 字段名/列名/属性名
		 Field[] fields = tObj.getClass().getDeclaredFields();
		
		for (Field field : fields) {
			
			//设置true可以反射出私有属性
			field.setAccessible(true);
			
			Column column = field.getAnnotation(Column.class);
			sbSql.append(column.value()).append(",");
			
		}
		sbSql.deleteCharAt(sbSql.length()-1);

		sbSql.append(" from ");
		
		//表名及where条件
		sbSql.append(table.value()).append(" ").append(whereSql);
		
		//输出设计好的sql语句
		//System.out.println(sbSql.toString());
		
		//下面链接数据库
		Connection con = getMysqlConnection();	
			
		//获取执行sql的preparedStatement对象
		PreparedStatement preparedStatement = con.prepareStatement(sbSql.toString());
		
		int count = 1;
		
		//给preparedStatement的？赋值
		for (Object value : objects) {
			
			preparedStatement.setObject(count++, value);
		}
		
		//执行sql语句，返回结果——查询
		ResultSet rs = preparedStatement.executeQuery();
		
		//boolean bln = rs.wasNull();
		//System.out.println(bln);	
		
		//把查到的数据赋予到新的USer对象中
		List<T> list = new ArrayList<>();			
			
		while(rs.next()) {
			
			T t = (T)tObj.getClass().getDeclaredConstructor().newInstance();
			
			for (Field field : fields) {
				
				field = tObj.getClass().getDeclaredField(field.getName());	
				//设置true可以反射出私有属性
				field.setAccessible(true);
				Column column = field.getAnnotation(Column.class);
				field.set(t,rs.getObject(column.value()));
			}
			list.add(t);
		}
		
		//关闭资源 先用的后关掉
		rs.close();
		preparedStatement.close();
		con.close();
		
		//返回这个含有T对象的list
		return list;		
	}
	
	/**
	 * 查找最大select
	 * @param tObj
	 * @return
	 * @throws SQLException 
	 */
	public int queryMysqlMaxId(T tObj,String select) throws SQLException {
		
		Table table = tObj.getClass().getAnnotation(Table.class);
		StringBuilder sbSql = new StringBuilder();
		sbSql.append("select max(");
		sbSql.append(select);
		sbSql.append(") from ");
		sbSql.append(table.value());
		
		Connection con = getMysqlConnection();
		PreparedStatement preparedStatement = con.prepareStatement(sbSql.toString());
		ResultSet rs = preparedStatement.executeQuery();
		
		int maxSelect = 0;
		
		while(rs.next()) {
			maxSelect=rs.getInt("MAX("+select+")");
		}
		
		rs.close();
		preparedStatement.close();
		con.close();
		
		return maxSelect;
	}
	
	/**
	 * sql更新   用于更新数据库数据
	 * @param tObj 与数据库表相对应的类的实例
	 * @param objects  
	 * @throws SQLException 
	 */
	public void updateMySql(T tObj,Object setValue,Object whereSql,Object ...objects) throws SQLException {
		
		Table table = tObj.getClass().getAnnotation(Table.class);
		
		StringBuilder sbSql = new StringBuilder();
		
		//update + 表名 + set
		sbSql.append(" update ").append(table.value()).append(" set ");
		
		//set的属性
		sbSql.append(setValue);
		sbSql.append(" = ? ");
		
		//where条件
		sbSql.append(whereSql);
		
		//输出设计好的sql
		//System.out.println(sbSql.toString());
		
		//下面链接数据库
		Connection con = getMysqlConnection();
		
		//获取执行sql的preparedStatement对象
		PreparedStatement preparedStatement = con.prepareStatement(sbSql.toString());
		
		//给preparedStatement的？赋值
		int count = 1;
		
		for (Object value : objects) {
			
			preparedStatement.setObject(count++, value);
		}
		//执行sql语句，返回结果——新增
		preparedStatement.executeUpdate();
		
		//关闭资源
		preparedStatement.close();
		con.close();
			
		
	}
	/**
	 * 连接MySql数据库
	 * @return
	 * @throws SQLException
	 */
	public Connection getMysqlConnection() throws SQLException {
		
		String url = CommonUtils.getValueFromProp("url");
		String sqlUser = CommonUtils.getValueFromProp("sqlUser");
		String sqlPasswd = CommonUtils.getValueFromProp("sqlPasswd");
		Connection con = DriverManager.getConnection(url,sqlUser,sqlPasswd);
		
		return con;
	}
}
