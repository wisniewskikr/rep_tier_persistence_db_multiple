package pl.kwi.daos;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.sql.Connection;
import java.util.List;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.kwi.db.jdbc.JdbcUtilTest;
import pl.kwi.entities.NameEntity;
import pl.kwi.entities.SurnameEntity;
import pl.kwi.entities.UserEntity;

public class UserDaoTest {
	
	
	private static UserDao dao;
	private static Connection conn;
	
	@BeforeClass
	public static void setUp() throws Exception{
		conn = JdbcUtilTest.beginConncetion(JdbcUtilTest.PROP_TEST_DB_URL_USERS);
		dao = new UserDao(conn);
	}
	
	@AfterClass
	public static void after(){
		JdbcUtilTest.finishConnection(conn);
	}

	@Test
	public void createUser() throws Exception{
		
		JdbcUtilTest.executeDataFile("/dbunit/userDaoTest.xml", conn);
		
		UserEntity user = new UserEntity();
		user.setNameEntity(new NameEntity(4L, null));
		user.setSurnameEntity(new SurnameEntity(4L, null));
		
		dao.createUser(user);
		
		Long id = user.getId();
		assertNotNull(id);
				
	}
	
	@Test
	public void readUser() throws Exception{
		
		JdbcUtilTest.executeDataFile("/dbunit/userDaoTest.xml", conn);
		
		UserEntity user = dao.readUser(1L);
		
		assertEquals(Long.valueOf(1), user.getId());
		assertEquals(Long.valueOf(1), user.getNameEntity().getId());
		assertEquals(Long.valueOf(1), user.getSurnameEntity().getId());
				
	}
	
	@Test
	public void updateUser() throws Exception{
		
		JdbcUtilTest.executeDataFile("/dbunit/userDaoTest.xml", conn);
		
		UserEntity user = new UserEntity();
		user.setId(1L);
		user.setNameEntity(new NameEntity(4L, null));
		user.setSurnameEntity(new SurnameEntity(4L, null));
		
		dao.updateUser(user);
		
		assertEquals(Long.valueOf(1), user.getId());
		assertEquals(Long.valueOf(4), user.getNameEntity().getId());
		assertEquals(Long.valueOf(4), user.getSurnameEntity().getId());
				
	}
	
	@Test
	public void deleteUser() throws Exception{
		
		JdbcUtilTest.executeDataFile("/dbunit/userDaoTest.xml", conn);
						
		dao.deleteUser(1L);		
		UserEntity user = dao.readUser(1L);
		
		Assert.assertNull(user);
				
	}
	
	@Test
	public void getUsers() throws Exception{
		
		JdbcUtilTest.executeDataFile("/dbunit/userDaoTest.xml", conn);
		
		List<UserEntity> users = dao.getUsers();
		
		assertEquals(3, users.size());
		
	}

}
