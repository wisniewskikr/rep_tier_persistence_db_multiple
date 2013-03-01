package pl.kwi.daos;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import pl.kwi.db.jpa.AbstractDaoNames;
import pl.kwi.entities.NameEntity;

@Repository
public class NameDao extends AbstractDaoNames<NameEntity> {
	
	public NameDao() {
		setClazz(NameEntity.class);
	}
	
	public NameDao(EntityManager em){
		super(em);
		setClazz(NameEntity.class);
	}
	
}
