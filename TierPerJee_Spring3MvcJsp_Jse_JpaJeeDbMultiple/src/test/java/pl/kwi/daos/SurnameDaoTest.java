package pl.kwi.daos;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.kwi.db.jpa.JpaUtilTest;
import pl.kwi.entities.SurnameEntity;

public class SurnameDaoTest {
	
	
	private SurnameDao dao;
	private EntityManager em;
	
	@Before
	public void setUp() throws Exception{		
		em = JpaUtilTest.beginEntityManager(JpaUtilTest.PROP_TEST_PERSISTENCE_UNIT_SURNAMES);
		dao = new SurnameDao(em);
	}
	
	@After
	public void after(){
		JpaUtilTest.finishEntityManager(em);
	}

	@Test
	public void createSurname() throws Exception{
		
		JpaUtilTest.executeDataFile("/dbunit/surnameDaoTest.xml", em);
		EntityTransaction tx = JpaUtilTest.beginTransaction(em);
		
		SurnameEntity surnameEntity = new SurnameEntity(null, "Surname4");
		
		dao.create(surnameEntity);
		
		JpaUtilTest.commitTransaction(tx);
		
		Long id = surnameEntity.getId();
		assertNotNull(id);
				
	}
	
	@Test
	public void readSurname() throws Exception{
		
		JpaUtilTest.executeDataFile("/dbunit/surnameDaoTest.xml", em);
		EntityTransaction tx = JpaUtilTest.beginTransaction(em);
		
		SurnameEntity surnameEntity = dao.read(1L);
		
		JpaUtilTest.commitTransaction(tx);
		
		assertEquals(Long.valueOf(1), surnameEntity.getId());
		assertEquals("Surname1", surnameEntity.getSurname());
				
	}
	
	@Test
	public void updateSurname() throws Exception{
		
		JpaUtilTest.executeDataFile("/dbunit/surnameDaoTest.xml", em);
		EntityTransaction tx = JpaUtilTest.beginTransaction(em);
		
		SurnameEntity surnameEntity = new SurnameEntity(1L, "Surname4");
		dao.update(surnameEntity);
		
		JpaUtilTest.commitTransaction(tx);
		
		assertEquals(Long.valueOf(1), surnameEntity.getId());
		assertEquals("Surname4", surnameEntity.getSurname());
				
	}
	
	@Test
	public void deleteSurname() throws Exception{
		
		JpaUtilTest.executeDataFile("/dbunit/surnameDaoTest.xml", em);
		EntityTransaction tx = JpaUtilTest.beginTransaction(em);
						
		dao.delete(1L, SurnameEntity.class);		
		SurnameEntity surnameEntity = dao.read(1L);
		
		JpaUtilTest.commitTransaction(tx);
		
		Assert.assertNull(surnameEntity);
				
	}

}
