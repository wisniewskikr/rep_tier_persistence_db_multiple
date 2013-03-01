package pl.kwi.daos;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.util.List;

import junit.framework.Assert;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pl.kwi.db.spring.DbUnitUtil;
import pl.kwi.entities.NameEntity;
import pl.kwi.entities.SurnameEntity;
import pl.kwi.entities.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/conf/spring-conf.xml",
		"/conf/spring-db-test.xml"
		})
@Transactional(value = "transactionManagerUsers")
public class UserDaoTest {

	@Autowired
	private UserDao dao;
	
	@Autowired
	@Qualifier("sessionFactoryUsers")
	public SessionFactory sessionFactory;
	
	@Test
	public void create() {
		
		DbUnitUtil.executeDataFile("/dbunit/userDaoTest.xml", sessionFactory);

		UserEntity user = new UserEntity();
		user.setNameEntity(new NameEntity(4L, null));
		user.setSurnameEntity(new SurnameEntity(4L, null));
		
		dao.create(user);
		
		Long id = user.getId();
		assertNotNull(id);

	}

	@Test
	public void read() {
		
		DbUnitUtil.executeDataFile("/dbunit/userDaoTest.xml", sessionFactory);
		
		UserEntity user = dao.findOne(1L);
		
		assertEquals(Long.valueOf(1), user.getId());
		assertEquals(Long.valueOf(1), user.getNameEntity().getId());
		assertEquals(Long.valueOf(1), user.getSurnameEntity().getId());

	}

	@Test
	public void update() {
		
		DbUnitUtil.executeDataFile("/dbunit/userDaoTest.xml", sessionFactory);

		UserEntity user = new UserEntity();
		user.setId(1L);
		user.setNameEntity(new NameEntity(4L, null));
		user.setSurnameEntity(new SurnameEntity(4L, null));
		
		dao.update(user);
		
		assertEquals(Long.valueOf(1), user.getId());
		assertEquals(Long.valueOf(4), user.getNameEntity().getId());
		assertEquals(Long.valueOf(4), user.getSurnameEntity().getId());

	}

	@Test
	public void delete() {
		
		DbUnitUtil.executeDataFile("/dbunit/userDaoTest.xml", sessionFactory);
		
		dao.deleteById(1L);		
		UserEntity user = dao.findOne(1L);
		
		Assert.assertNull(user);

	}
	
	@Test
	public void getAllSimpleEntity(){
		
		DbUnitUtil.executeDataFile("/dbunit/userDaoTest.xml", sessionFactory);
		
		List<UserEntity> users = dao.findAll();
		
		assertEquals(3, users.size());
		
	}

}
