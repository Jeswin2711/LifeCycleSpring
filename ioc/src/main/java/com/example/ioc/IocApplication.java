package com.example.ioc;

import java.sql.SQLException;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class IocApplication {
	
	public static void main(String[] args) throws SQLException {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		FriendsList friend = context.getBean("friends",FriendsList.class);
		friend.showData();
		friend.deleteData(7);
		context.close();
	}

}
