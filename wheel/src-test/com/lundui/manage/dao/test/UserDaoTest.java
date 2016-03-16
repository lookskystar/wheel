package com.lundui.manage.dao.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lundui.manage.system.dao.UserDao;

public class UserDaoTest {
	
	ApplicationContext context;
	
	@Before
	public void before(){
		context = new FileSystemXmlApplicationContext("classpath:springConfig/applicationContext-*.xml"); 
	}
	
	@Test
	public void test(){
		UserDao dao=(UserDao)context.getBean("userDao");
	}

}
