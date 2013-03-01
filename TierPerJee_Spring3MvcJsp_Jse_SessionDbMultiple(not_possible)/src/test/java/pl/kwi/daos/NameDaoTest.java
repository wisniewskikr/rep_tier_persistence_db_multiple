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
import pl.kwi.entities.NameEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/conf/spring-conf.xml",
		"/conf/spring-db-test.xml"
		})
@Transactional(value = "transactionManagerNames")
public class NameDaoTest {
	
	
	@Autowired
	private NameDao dao;
	
	@Autowired
	@Qualifier("sessionFactoryNames")
	public SessionFactory sessionFactory;
	

	@Test
	public void createName() throws Exception{
		
		DbUnitUtil.executeDataFile("/dbunit/nameDaoTest.xml", sessionFactory);
		
		NameEntity nameEntity = new NameEntity(null, "Name4");
		
		dao.create(nameEntity);
		
		Long id = nameEntity.getId();
		assertNotNull(id);
				
	}
	
	@Test
	public void readName() throws Exception{
		
		DbUnitUtil.executeDataFile("/dbunit/nameDaoTest.xml", sessionFactory);
		
		NameEntity nameEntity = dao.findOne(1L);
		
		assertEquals(Long.valueOf(1), nameEntity.getId());
		assertEquals("Name1", nameEntity.getName());
				
	}
	
	@Test
	public void updateName() throws Exception{
		
		DbUnitUtil.executeDataFile("/dbunit/nameDaoTest.xml", sessionFactory);
		
		NameEntity nameEntity = new NameEntity(1L, "Name4");
		
		dao.update(nameEntity);
		
		assertEquals(Long.valueOf(1), nameEntity.getId());
		assertEquals("Name4", nameEntity.getName());
				
	}
	
	@Test
	public void deleteName() throws Exception{
		
		DbUnitUtil.executeDataFile("/dbunit/nameDaoTest.xml", sessionFactory);
						
		dao.deleteById(1L);		
		NameEntity nameEntity = dao.findOne(1L);
		
		Assert.assertNull(nameEntity);
				
	}

}
