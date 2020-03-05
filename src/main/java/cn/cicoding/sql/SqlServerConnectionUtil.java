package cn.cicoding.sql;

import java.sql.Connection;
import java.sql.DriverManager;

import cn.cicoding.other.PropertyUtil;

public class SqlServerConnectionUtil {

	private String driverName = PropertyUtil.getProperty("sqlserver.driverClassName");
	private String url = PropertyUtil.getProperty("sqlserver.url") ;
	private String user = PropertyUtil.getProperty("sqlserver.username");
	private String password = PropertyUtil.getProperty("sqlserver.password");
	
	public void setDriverName(String newDriverName) {  
        driverName = newDriverName;  
    }  
    public String getDriverName() {  
        return driverName;  
    }  
      
    public void setUrl(String newUrl) {  
        url = newUrl;  
    }  
    public String getUrl() {  
        return url;  
    }  
    public void setUser(String newUser) {  
        user = newUser;  
    }  
    public String getUser() {  
        return user;  
    }  
    public void setPassword(String newPassword) {  
        password = newPassword;  
    }  
    public String getPassword() {  
        return password;  
    }  
    public Connection getConnection() {  
        try {  
            Class.forName(driverName); 
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }
    
    public Connection getConnection(String driverName,String url,String user,String password) {
        try {
            Class.forName(driverName);
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
