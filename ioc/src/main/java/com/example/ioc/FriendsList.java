package com.example.ioc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class FriendsList {
	
	private String url;
    private String driver;
    private String userName;
    private String passWord;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
    Connection connection;
    Statement statement;
    
    @PostConstruct
    public void init() throws SQLException , ClassNotFoundException
    {
    	System.out.println("\n");
    	System.out.println("Connecting to Database.... \n");
    	createDataBaseConnection();
    }

    public void createDataBaseConnection() throws SQLException, ClassNotFoundException {
    	Class.forName(driver);
        connection = DriverManager.getConnection(url,userName,passWord);
        statement = connection.createStatement();

    }

    public void showData() throws SQLException {
        statement.executeQuery("select * from friend_list");
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next())
        {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int age = resultSet.getInt(3);
            System.out.println("ID : " + id);
            System.out.println("NAME : " + name);
            System.out.println("AGE : " + age);
            System.out.println("\n");
        }
    }
    
    public void deleteData(int id) throws SQLException
    {
    	statement.executeUpdate("delete from friend_list where id = "+ id);
    	System.out.println("------- After Deleting ID = "+id+" -------\n");
    	showData();
    }
    
   
    public void closeConnection() throws SQLException
    {
    	connection.close();
    }
    
    @PreDestroy
    public void destroy() throws SQLException
    {
    	System.out.println("Closing Connection...");
    	closeConnection();
    }
    

}
