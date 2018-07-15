package com.hkq.dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 数据库操作工具类，提供以下方法<br/>
 * 1. 获取Connection连接对象<br/>
 * 2. 关闭Connection, Statement, ResultSet对象<br/>
 * 3. 执行一条无返回值的Sql语句，返回执行后受影响的记录数，如：增删改<br/>
 * 4. 执行一条有返回值的Sql语句<br/>
 * 
 * 数据库配置文件必须为/WEB-INF/db-config.properites
 * 
 * @author hkq
 */

public class DBUtil {
	
	private static String driver;
	private static String url;
	private static String name;
	private static String pass;
	
	static {
		ResourceBundle config = ResourceBundle.getBundle("com.hkq.dao.db-config");
		driver = config.getString("jdbc-driver");
		url = config.getString("jdbc-url");
		name = config.getString("jdbc-name");
		pass = config.getString("jdbc-pass");
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("读取数据库文件或加载数据库驱动错误");
		} 
	}
	
	/**
	 * 获取连接对象，失败返回null
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, name, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 依次关闭ResultSet、Statement、Connection对象。若参数为空，则什么也不做
	 */
	public static void close(Connection conn, Statement state, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		
		if(state != null) {
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			state = null;
		}
		
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
	/**
	 * 执行一条无返回值的sql语句(如：增删改)，返回受影响的行数 <br/>
	 * 不保证将parmas中的null映射为数据库中的空，如需要可调用executeUpdateWithNull()函数
	 * 
	 * @throws SQLException 
	 */
	public static int executeUpdate(String sql, Object[] params) throws SQLException {
		return executeUpdateWithNull(sql, params, null);
	}
	
	/**
	 * 执行一条无返回值的sql语句(如：增删改)，返回受影响的行数，会将parmas中的null映射为数据库中的空 <br/>
	 * 
	 * 如果sqlType为null，则与调用executeUpdate()函数相同
	 * 
	 * @param sqlTypes params参数对应的SqlType类型
	 * @throws SQLException 
	 */
	public static int executeUpdateWithNull(String sql, Object[] params, int[] sqlTypes) throws SQLException {
		Connection conn = null;
		PreparedStatement pstate = null;
		int result = 0;
		
		try {
			conn = getConnection();
			pstate = conn.prepareStatement(sql);
			if(sqlTypes == null) {
				fillParams(pstate, params);
			} else {
				fillParamsWithNull(pstate, params, sqlTypes);
			}
			result = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(conn, pstate, null);
		}
		return result;
	}
	
	/**
	 * 执行有返回值的sql语句，并保证返回的List中记录顺序与查询结果顺序一致<br/>
	 * 
	 * @return 一定不为null，如果无返回记录，则List长度为0
	 * @throws SQLException 
	 */
	public static List<Map<String, Object>> executeQuery(String sql, Object[] params) throws SQLException {
		Connection conn = null;
		PreparedStatement pstate = null;
		ResultSet rs = null;
		List<Map<String, Object>> table = new ArrayList<>();
		
		try {
			conn = getConnection();
			pstate = conn.prepareStatement(sql);
			fillParams(pstate, params);
			rs = pstate.executeQuery();
			
			// 获取列的基本信息，如列名、列数量
			ResultSetMetaData metaData = rs.getMetaData();
			int columns = metaData.getColumnCount();
			String[] columnNames = new String[columns];
			for(int i = 0; i < columns; i++) {
				columnNames[i] = metaData.getColumnName(i + 1);
			}
			// 将rs中的数据封装返回
			while(rs.next()) {
				Map<String, Object> row = new HashMap<>();
				for(String name : columnNames) {
					row.put(name, rs.getObject(name));
				}
				table.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(conn, pstate, rs);
		}
		
		return table;
	}
	
	/**
	 * 向PreparedSatement对象填充参数，如果params为空，则什么也不做<br/>
	 * 
	 * @throws SQLException
	 */
	private static void fillParams(PreparedStatement pstate, Object[] params) throws SQLException {
		if(params == null) {
			return ;
		}
		
		for(int i = 0; i < params.length; i++) {
			pstate.setObject(i + 1, params[i]);
		}
	}
	
	/**
	 * 根据sqlTypes中的参数类型向PreparedStatement对象填充参数，如果params中有null，会将其映射为数据库中的空<br/>
	 * 
	 * 如果params为null，则什么也不做<br/>
	 * 如果sqlTypes为null，则与调用fillParams函数相同<br/>
	 * 
	 * @param sqlTypes params参数对应的SqlType类型
	 * 
	 * @throws SQLException
	 * @throws RunTimeException 当sqlTypes != null && params.length != sqlTypes.length
	 */
	private static void fillParamsWithNull(PreparedStatement pstate, Object[] params, int[] sqlTypes) throws SQLException {
		if(params == null) {
			return ;
		}
		if(sqlTypes == null) {
			fillParams(pstate, params);
			return ;
		}
		if(sqlTypes.length != params.length) {
			throw new RuntimeException("parmas与sqlTypes参数不匹配");
		}
		
		for(int i = 0; i < params.length; i++) {
			if(params[i] == null) {
				pstate.setNull(i + 1, sqlTypes[i]);
			} else {
				pstate.setObject(i + 1, params[i], sqlTypes[i]);
			}
		}
	}
	
}
