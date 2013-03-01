package pl.kwi.daos;

import javax.persistence.EntityManager;

import pl.kwi.db.jpa.AbstractDao;
import pl.kwi.entities.SurnameEntity;

public class SurnameDao extends AbstractDao<SurnameEntity> {
	
	public SurnameDao() {
		setClazz(SurnameEntity.class);
	}
	
	public SurnameDao(EntityManager em){
		super(em);
		setClazz(SurnameEntity.class);
	}
	
}
