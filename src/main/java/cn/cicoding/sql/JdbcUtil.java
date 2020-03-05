package cn.cicoding.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
* @Description: JDBC连接数据库
* @author WangPan
* @date 2018年9月4日
* @version V1.0
*/
public class JdbcUtil {

	private static final String SQL_SERVER_DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String MY_SQL_DRIVER_NAME = "com.mysql.jdbc.Driver";
	
	/**
	* @Description: TODO
	* @param @param type 数据库类型
	* @param @param ip 数据库IP
	* @param @param port 数据库端口
	* @param @param dataBaseName 数据库名
	* @param @param username 用户
	* @param @param password 密码
	* @param @param sql sql语句
	* @param @return    
	* @return List<Object[]>
	* @date 2017-6-22 上午08:35:40 
	* @author wangp 
	*/ 
	public static List<Object[]> executeJdbcQeury(String type,String ip,String port,String dataBaseName,String username,String password,String sql){
		List<Object[]> list = new ArrayList<Object[]>();
		if(type != null && type.trim().length() > 0 
				&& ip != null && ip.trim().length() > 0 
				&& port != null && port.trim().length() > 0 
				&& dataBaseName != null && dataBaseName.trim().length() > 0 
				&& username != null && username.trim().length() > 0 
				&& password != null && password.trim().length() > 0 
				&& sql != null && sql.trim().length() > 0){
			String url = null;
			String driverName = null;
			if(type.equalsIgnoreCase("sqlserver")){
				url = "jdbc:sqlserver://" + ip + ":" + port + ";databaseName=" + dataBaseName;
				driverName = SQL_SERVER_DRIVER_NAME;
			}else if(type.equalsIgnoreCase("mysql")){
				url = "jdbc:mysql://" + ip + ":" + port + "/" + dataBaseName + "?useUnicode=true&characterEncoding=utf8&autoReconnect=true";
				driverName = MY_SQL_DRIVER_NAME;
			}
			
			Connection connection = null;
		    Statement stmt = null;
		    try {
				Class.forName(driverName);
				connection = DriverManager.getConnection(url, username, password);
				stmt = connection.createStatement();
				ResultSet result = stmt.executeQuery(sql);
				
				while(result.next()){
					ResultSetMetaData rsmd = result.getMetaData();
					int columnCount = rsmd.getColumnCount();
					Object[] obj = new Object[columnCount];
					if(columnCount > 0){
						for(int i = 1; i <= columnCount; i ++){
							obj[i - 1] = result.getObject(i);
						}
					}
					list.add(obj);
				}
				result.close();
				stmt.close();
				connection.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				
			}
		}
		return list;
	}
}
