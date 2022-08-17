package com.example.ioc;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IocApplication {
	
	public static void main(String[] args) throws SQLException {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		FriendsList friend = context.getBean("friends",FriendsList.class);
		friend.showData();
		friend.deleteData(5);
		((ClassPathXmlApplicationContext)context).close();
	}

}
