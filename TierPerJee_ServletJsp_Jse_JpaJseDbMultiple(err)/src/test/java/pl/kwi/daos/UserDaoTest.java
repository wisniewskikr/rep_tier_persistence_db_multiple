package pl.kwi.daos;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.kwi.db.jpa.JpaUtilTest;
import pl.kwi.entities.NameEntity;
import pl.kwi.entities.SurnameEntity;
import pl.kwi.entities.UserEntity;

public class UserDaoTest {
	
	
	private UserDao dao;
	private EntityManager em;
	
	
	@Before
	public void setUp() throws Exception{
		em = JpaUtilTest.beginEntityManager(JpaUtilTest.PROP_TEST_PERSISTENCE_UNIT_USERS);
		dao = new UserDao(em);
	}
	
	@After
	public void after(){
		JpaUtilTest.finishEntityManager(em);
	}

	@Test
	public void createUser() throws Exception{
		
		JpaUtilTest.executeDataFile("/dbunit/userDaoTest.xml", em);		
		EntityTransaction tx = JpaUtilTest.beginTransaction(em);
		
		UserEntity user = new UserEntity();
		user.setNameEntity(new NameEntity(4L, null));
		user.setSurnameEntity(new SurnameEntity(4L, null));
		
		dao.create(user);
		
		JpaUtilTest.commitTransaction(tx);
		
		Long id = user.getId();
		assertNotNull(id);
				
	}
	
	@Test
	public void readUser() throws Exception{
		
		JpaUtilTest.executeDataFile("/dbunit/userDaoTest.xml", em);		
		EntityTransaction tx = JpaUtilTest.beginTransaction(em);
		
		UserEntity user = dao.read(1L);
		
		JpaUtilTest.commitTransaction(tx);
		
		assertEquals(Long.valueOf(1), user.getId());
		assertEquals(Long.valueOf(1), user.getNameEntity().getId());
		assertEquals(Long.valueOf(1), user.getSurnameEntity().getId());
				
	}
	
	@Test
	public void updateUser() throws Exception{
		
		JpaUtilTest.executeDataFile("/dbunit/userDaoTest.xml", em);
		EntityTransaction tx = JpaUtilTest.beginTransaction(em);
		
		UserEntity user = new UserEntity();
		user.setId(1L);
		user.setNameEntity(new NameEntity(4L, null));
		user.setSurnameEntity(new SurnameEntity(4L, null));
		
		dao.update(user);
		
		JpaUtilTest.commitTransaction(tx);
		
		assertEquals(Long.valueOf(1), user.getId());
		assertEquals(Long.valueOf(4), user.getNameEntity().getId());
		assertEquals(Long.valueOf(4), user.getSurnameEntity().getId());
				
	}
	
	@Test
	public void deleteUser() throws Exception{
		
		JpaUtilTest.executeDataFile("/dbunit/userDaoTest.xml", em);
		EntityTransaction tx = JpaUtilTest.beginTransaction(em);
						
		dao.delete(1L, UserEntity.class);		
		UserEntity user = dao.read(1L);
		
		JpaUtilTest.commitTransaction(tx);
		
		Assert.assertNull(user);
				
	}
	
	@Test
	public void getUsers() throws Exception{
		
		JpaUtilTest.executeDataFile("/dbunit/userDaoTest.xml", em);
		EntityTransaction tx = JpaUtilTest.beginTransaction(em);
		
		@SuppressWarnings("unchecked")
		List<UserEntity> users = (List<UserEntity>) dao.readAll();
		
		JpaUtilTest.commitTransaction(tx);
		
		assertEquals(3, users.size());
		
	}

}
