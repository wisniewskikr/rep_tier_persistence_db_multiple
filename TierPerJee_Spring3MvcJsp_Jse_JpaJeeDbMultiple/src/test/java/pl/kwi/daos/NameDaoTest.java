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
import pl.kwi.entities.NameEntity;

public class NameDaoTest {
	
	
	private  NameDao dao;
	private  EntityManager em;
	
	@Before
	public void setUp() throws Exception{		
		em = JpaUtilTest.beginEntityManager(JpaUtilTest.PROP_TEST_PERSISTENCE_UNIT_NAMES);
		dao = new NameDao(em);
	}
	
	@After
	public void after(){
		JpaUtilTest.finishEntityManager(em);
	}

	@Test
	public void createName() throws Exception{
				
		JpaUtilTest.executeDataFile("/dbunit/nameDaoTest.xml", em);
		EntityTransaction tx = JpaUtilTest.beginTransaction(em);
		
		NameEntity nameEntity = new NameEntity(null, "Name4");		
		dao.create(nameEntity);
		
		JpaUtilTest.commitTransaction(tx);
		
		Long id = nameEntity.getId();
		assertNotNull(id);
				
	}
	
	@Test
	public void readName() throws Exception{
		
		JpaUtilTest.executeDataFile("/dbunit/nameDaoTest.xml", em);
		EntityTransaction tx = JpaUtilTest.beginTransaction(em);
		
		NameEntity nameEntity = dao.read(1L);		
		
		JpaUtilTest.commitTransaction(tx);
		
		assertEquals(Long.valueOf(1), nameEntity.getId());
		assertEquals("Name1", nameEntity.getName());
				
	}
	
	@Test
	public void updateName() throws Exception{
		
		JpaUtilTest.executeDataFile("/dbunit/nameDaoTest.xml", em);
		EntityTransaction tx = JpaUtilTest.beginTransaction(em);
		
		NameEntity nameEntity = new NameEntity(1L, "Name4");		
		dao.update(nameEntity);
		
		JpaUtilTest.commitTransaction(tx);
		
		assertEquals(Long.valueOf(1), nameEntity.getId());
		assertEquals("Name4", nameEntity.getName());
				
	}
	
	@Test
	public void deleteName() throws Exception{
		
		JpaUtilTest.executeDataFile("/dbunit/nameDaoTest.xml", em);
		EntityTransaction tx = JpaUtilTest.beginTransaction(em);
						
		dao.delete(1L, NameEntity.class);		
		NameEntity nameEntity = dao.read(1L);
		
		JpaUtilTest.commitTransaction(tx);
		
		Assert.assertNull(nameEntity);
				
	}

}
