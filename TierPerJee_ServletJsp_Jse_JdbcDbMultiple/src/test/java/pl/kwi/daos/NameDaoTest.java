package pl.kwi.daos;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.sql.Connection;
import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.kwi.db.jdbc.JdbcUtilTest;
import pl.kwi.entities.NameEntity;

public class NameDaoTest {
	
	
	private static NameDao dao;
	private static Connection conn;
	
	@BeforeClass
	public static void setUp() throws Exception{
		conn = JdbcUtilTest.beginConncetion(JdbcUtilTest.PROP_TEST_DB_URL_NAMES);
		dao = new NameDao(conn);
	}
	
	@AfterClass
	public static void after(){
		JdbcUtilTest.finishConnection(conn);
	}

	@Test
	public void createName() throws Exception{
		
		JdbcUtilTest.executeDataFile("/dbunit/nameDaoTest.xml", conn);
		
		NameEntity nameEntity = new NameEntity(null, "Name4");
		
		dao.createName(nameEntity);
		
		Long id = nameEntity.getId();
		assertNotNull(id);
				
	}
	
	@Test
	public void readName() throws Exception{
		
		JdbcUtilTest.executeDataFile("/dbunit/nameDaoTest.xml", conn);
		
		NameEntity nameEntity = dao.readName(1L);
		
		assertEquals(Long.valueOf(1), nameEntity.getId());
		assertEquals("Name1", nameEntity.getName());
				
	}
	
	@Test
	public void updateName() throws Exception{
		
		JdbcUtilTest.executeDataFile("/dbunit/nameDaoTest.xml", conn);
		
		NameEntity nameEntity = new NameEntity(1L, "Name4");
		
		dao.updateName(nameEntity);
		
		assertEquals(Long.valueOf(1), nameEntity.getId());
		assertEquals("Name4", nameEntity.getName());
				
	}
	
	@Test
	public void deleteName() throws Exception{
		
		JdbcUtilTest.executeDataFile("/dbunit/nameDaoTest.xml", conn);
						
		dao.deleteName(1L);		
		NameEntity nameEntity = dao.readName(1L);
		
		Assert.assertNull(nameEntity);
				
	}

}
