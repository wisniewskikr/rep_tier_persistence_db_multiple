package pl.kwi.daos;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

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
import pl.kwi.entities.SurnameEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/conf/spring-conf.xml",
		"/conf/spring-db-test.xml"
		})
@Transactional(value = "transactionManagerSurnames")
public class SurnameDaoTest {
	
	
	@Autowired
	private SurnameDao dao;
	
	@Autowired
	@Qualifier("sessionFactorySurnames")
	public SessionFactory sessionFactory;
	

	@Test
	public void createSurname() throws Exception{
		
		DbUnitUtil.executeDataFile("/dbunit/surnameDaoTest.xml", sessionFactory);
		
		SurnameEntity surnameEntity = new SurnameEntity(null, "Surname4");
		
		dao.create(surnameEntity);
		
		Long id = surnameEntity.getId();
		assertNotNull(id);
				
	}
	
	@Test
	public void readSurname() throws Exception{
		
		DbUnitUtil.executeDataFile("/dbunit/surnameDaoTest.xml", sessionFactory);
		
		SurnameEntity surnameEntity = dao.findOne(1L);
		
		assertEquals(Long.valueOf(1), surnameEntity.getId());
		assertEquals("Surname1", surnameEntity.getSurname());
				
	}
	
	@Test
	public void updateSurname() throws Exception{
		
		DbUnitUtil.executeDataFile("/dbunit/surnameDaoTest.xml", sessionFactory);
		
		SurnameEntity surnameEntity = new SurnameEntity(1L, "Surname4");
		
		dao.update(surnameEntity);
		
		assertEquals(Long.valueOf(1), surnameEntity.getId());
		assertEquals("Surname4", surnameEntity.getSurname());
				
	}
	
	@Test
	public void deleteSurname() throws Exception{
		
		DbUnitUtil.executeDataFile("/dbunit/surnameDaoTest.xml", sessionFactory);
						
		dao.deleteById(1L);		
		SurnameEntity surnameEntity = dao.findOne(1L);
		
		Assert.assertNull(surnameEntity);
				
	}

}
