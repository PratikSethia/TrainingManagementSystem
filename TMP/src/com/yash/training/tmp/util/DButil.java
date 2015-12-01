package com.yash.training.tmp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.sql.DataSource;

public class DButil {
	
	  //1. Collect DB Information
    static String driverClassName="com.mysql.jdbc.Driver";
    static String url="jdbc:mysql://localhost/trainingmanagement";
    static String userName="root";
    static String password="root";
    static Connection connection;
    /**
     * This method is used to connect the DB, all the exceptions are 
     * handled by the JVM
     */
    
    @Resource(lookup="java:jboss/datasources/DataDS")
	static
    DataSource dataSource;
    
    public static Connection connectDB()throws Exception{
        Class.forName(driverClassName);
        connection=DriverManager.getConnection(url, userName, password);
        return connection;        
    }
    
    /**
     * This method is common for all - this will perform 
     * Insertion, Deletion and Updation in tables
     */
    
    public static void update(String sql) throws Exception{
    	 connection = connectDB();
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.executeUpdate(); // not selecte statement, insertion, deletion, updation 
        disconnectDB();
    }
    
    /**
     * This method is common for all- and will be returning the ResultSet 
     * object which will be received in ResultSet reference. 
     * 
     */
    public static ResultSet select(String sql){
        try{
        	connection = connectDB();
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        ResultSet resultSet=preparedStatement.executeQuery();
        return resultSet;
        }
        catch(Exception ex){
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * This method is used to disconnect the DB
     * 
     */
    public static void disconnectDB() throws Exception{
        connection.close();
    }

}
