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
import com.feicuiedu.atm.entity.TradeRecord;
import com.feicuiedu.atm.entity.User;
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
		Field[] fileds = tObj.getClass().getDeclaredFields();
		
		for (Field field : fileds) {
			
			//设置true可以反射出私有属性
			field.setAccessible(true);
			Column column = field.getAnnotation(Column.class);
			sbSql.append(column.value()).append(",");
		}
		
		sbSql.deleteCharAt(sbSql.length()-1);
		sbSql.append(" ) values ( ");
		
		for (Field field : fileds) {
			
			field.setAccessible(true);
			sbSql.append("?, ");
		}
		
		sbSql.deleteCharAt(sbSql.length()-2);
		sbSql.append(" ) ");
		
		//输出设计好的sql语句
		//System.out.println(sbSql.toString());
		
		//下面链接数据库
		Connection con = getMysqlConnection();
		
		try {
			
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
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * sql查询   根据条件查出具体的一条数据
	 * @param tObj 与数据库表相对应的类的实例
	 * @param objects  1.sql的where查询条件 2.查询条件max(id)或表内全部列
	 * 				    
	 * @return 根据条件查出的user对象  
	 * @throws SQLException 
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryUserMySql(T tObj,Object ...objects) throws SQLException {
		
		Table table = tObj.getClass().getAnnotation(Table.class);
		
		StringBuilder sbSql = new StringBuilder();
		
		sbSql.append(" select ");
		
		Field[] fields = null;
		
		// 字段名/列名/属性名
		fields = tObj.getClass().getDeclaredFields();
		
		for (Field field : fields) {
			
			//设置true可以反射出私有属性
			field.setAccessible(true);
			
			Column column = field.getAnnotation(Column.class);
			sbSql.append(column.value()).append(",");
			
		}
		sbSql.deleteCharAt(sbSql.length()-1);
		

		sbSql.append(" from ");
		
		//表名及where条件
		sbSql.append(table.value()).append(" ").append(objects[0]);
		
		//输出设计好的sql语句
		//System.out.println(sbSql.toString());
		
		//下面链接数据库
		Connection con = getMysqlConnection();

		try {
			
			//获取执行sql的preparedStatement对象
			PreparedStatement preparedStatement = con.prepareStatement(sbSql.toString());
			
			for (int i = 1; i < objects.length; i++) {
				
				preparedStatement.setObject(i, objects[i]);
			}
			
			//执行sql语句，返回结果——查询
			ResultSet rs = preparedStatement.executeQuery();
			
			//boolean bln = rs.wasNull();
			//System.out.println(bln);	
			
			//把查到的数据赋予到新的USer对象中
			List<T> list = new ArrayList<>();			
			
			if("atm_user".equals(table.value())) {
				
				while(rs.next()) {
					T t = null;
					try {
						t = (T) tObj.getClass().getDeclaredConstructor().newInstance();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					((User) t).setId(rs.getInt("id"));
					((User) t).setAccount(rs.getString("account"));
					((User) t).setName(rs.getString("user_name"));
					((User) t).setAmount(rs.getDouble("amount"));
					((User) t).setGender(rs.getInt("gender"));
					((User) t).setCard(rs.getString("card"));
					((User) t).setUserType(rs.getString("userType"));
					((User) t).setPasswd(rs.getString("passwd"));
					((User) t).setBirthday(rs.getDate("birthday"));
					((User) t).setAddress(rs.getString("address"));
					((User) t).setRemark(rs.getString("remark"));
					 
					 list.add(t);
					 
				}
			}
			if("trade_record".equals(table.value())) {
				
				while(rs.next()) {
					
					T t = null;
					try {
						t = (T) tObj.getClass().getDeclaredConstructor().newInstance();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 ((TradeRecord) t).setId(rs.getInt("id"));
					 ((TradeRecord) t).setAccount(rs.getString("account"));
					 ((TradeRecord) t).setTargetAccount(rs.getString("targetAccount"));
					 ((TradeRecord) t).setTradeType(rs.getInt("tradeType"));
					 ((TradeRecord) t).setTradeDate(rs.getDate("tradeDate"));
					 ((TradeRecord) t).setTradeAmount(rs.getDouble("tradeAmount"));
					 ((TradeRecord) t).setAmount(rs.getDouble("amount"));
					 
					 list.add(t);
				}
				
			}
			
			//关闭资源 先用的后关掉
			rs.close();
			preparedStatement.close();
			con.close();
			
			//返回这个T对象
			return list;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 查找最大id
	 * @param tObj
	 * @return
	 * @throws SQLException 
	 */
	public int queryMysqlMaxId(T tObj) throws SQLException {
		
		Table table = tObj.getClass().getAnnotation(Table.class);
		StringBuilder sbSql = new StringBuilder();
		sbSql.append("select max(id) from ") ;
		sbSql.append(table.value());
		
		//System.out.println(sbSql.toString());
		
		Connection con = getMysqlConnection();
		PreparedStatement preparedStatement = con.prepareStatement(sbSql.toString());
		ResultSet rs = preparedStatement.executeQuery();
		
		int maxId=0;
		
		while(rs.next()) {
			maxId=rs.getInt("MAX(id)");
		}
		
		rs.close();
		preparedStatement.close();
		con.close();
		
		return maxId;
	}
	
	/**
	 * sql更新   用于更新数据库数据
	 * @param tObj 与数据库表相对应的类的实例
	 * @param objects  1.需要更新数据的列名(passwd/amount/userType) 2.需要更新的数据 3.更新的账户{
	 * 					objects[0] 修改的数据的列名
	 * 					objects[1] 修改数据的内容
	 * 					objects[2] where条件account or card
	 * @throws SQLException 
	 */
	public void updateMySql(T tObj,Object ...objects) throws SQLException {
		
		Table table = tObj.getClass().getAnnotation(Table.class);
		
		StringBuilder sbSql = new StringBuilder();
		
		//update + 表名 + set
		sbSql.append(" update ").append(table.value()).append(" set ");
		
		//set的属性
		sbSql.append(objects[0]+" = ? ");
		
		//where条件
		sbSql.append(" where account = ? or card = ?");
		
		//输出设计好的sql
		//System.out.println(sbSql.toString());
		
		//下面链接数据库
		Connection con = getMysqlConnection();
		
		try {
			
			//获取执行sql的preparedStatement对象
			PreparedStatement preparedStatement = con.prepareStatement(sbSql.toString());
			
			//给preparedStatement的？赋值
			preparedStatement.setObject(1, objects[1]);
			preparedStatement.setObject(2, objects[2]);
			preparedStatement.setObject(3, objects[2]);
			
			//执行sql语句，返回结果——新增
			preparedStatement.executeUpdate();
			
			//关闭资源
			preparedStatement.close();
			con.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public Connection getMysqlConnection() {
		
		String url = CommonUtils.getValueFromProp("Url");
		String sqlUser = CommonUtils.getValueFromProp("SqlUser");
		String sqlPasswd = CommonUtils.getValueFromProp("SqlPasswd");

		Connection con = null;
		try {
			con = DriverManager.getConnection(url,sqlUser,sqlPasswd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
}
