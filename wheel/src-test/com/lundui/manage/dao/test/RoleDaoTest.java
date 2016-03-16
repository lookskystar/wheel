package com.lundui.manage.dao.test;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lundui.manage.model.Role;
import com.lundui.manage.system.dao.RoleDao;
import com.lundui.manage.util.PageModel;
import com.lundui.manage.util.SystemContext;

public class RoleDaoTest {
	
ApplicationContext context;
	
	@Before
	public void before(){
		context = new FileSystemXmlApplicationContext("classpath:springConfig/applicationContext-*.xml"); 
	}
	
	public void testAdd(){
		RoleDao dao=(RoleDao)context.getBean("roleDao");
		Role role=new Role();
		role.setRoleName("车辆段工长");
		role.setRoleMark("CLDGZ");
		dao.saveEntity(role);
		System.out.println(role.getId());
	}
	
	@Test
	public void testQuery(){
		RoleDao dao=(RoleDao)context.getBean("roleDao");
		SystemContext.setOffset(0);
		SystemContext.setPageSize(10);
		PageModel<Role> page=dao.findPageModel(new Criterion[0]);
		List<Role> list=page.getDatas();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getRoleName());
		}
		System.out.println(page.getCount());
	}

}
