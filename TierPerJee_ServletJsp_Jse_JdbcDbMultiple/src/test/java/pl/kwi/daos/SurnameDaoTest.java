package pl.kwi.daos;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.sql.Connection;
import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.kwi.db.jdbc.JdbcUtilTest;
import pl.kwi.entities.SurnameEntity;

public class SurnameDaoTest {
	
	
	private static SurnameDao dao;
	private static Connection conn;
	
	@BeforeClass
	public static void setUp() throws Exception{
		conn = JdbcUtilTest.beginConncetion(JdbcUtilTest.PROP_TEST_DB_URL_SURNAMES);
		dao = new SurnameDao(conn);
	}
	
	@AfterClass
	public static void after(){
		JdbcUtilTest.finishConnection(conn);
	}

	@Test
	public void createSurname() throws Exception{
		
		JdbcUtilTest.executeDataFile("/dbunit/surnameDaoTest.xml", conn);
		
		SurnameEntity surnameEntity = new SurnameEntity(null, "Surname4");
		
		dao.createSurname(surnameEntity);
		
		Long id = surnameEntity.getId();
		assertNotNull(id);
				
	}
	
	@Test
	public void readSurname() throws Exception{
		
		JdbcUtilTest.executeDataFile("/dbunit/surnameDaoTest.xml", conn);
		
		SurnameEntity surnameEntity = dao.readSurname(1L);
		
		assertEquals(Long.valueOf(1), surnameEntity.getId());
		assertEquals("Surname1", surnameEntity.getSurname());
				
	}
	
	@Test
	public void updateSurname() throws Exception{
		
		JdbcUtilTest.executeDataFile("/dbunit/surnameDaoTest.xml", conn);
		
		SurnameEntity surnameEntity = new SurnameEntity(1L, "Surname4");
		
		dao.updateSurname(surnameEntity);
		
		assertEquals(Long.valueOf(1), surnameEntity.getId());
		assertEquals("Surname4", surnameEntity.getSurname());
				
	}
	
	@Test
	public void deleteSurname() throws Exception{
		
		JdbcUtilTest.executeDataFile("/dbunit/surnameDaoTest.xml", conn);
						
		dao.deleteSurname(1L);		
		SurnameEntity surnameEntity = dao.readSurname(1L);
		
		Assert.assertNull(surnameEntity);
				
	}

}
