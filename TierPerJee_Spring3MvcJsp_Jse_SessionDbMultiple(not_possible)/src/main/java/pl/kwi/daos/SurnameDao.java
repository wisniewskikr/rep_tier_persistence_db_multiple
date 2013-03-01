package pl.kwi.daos;

import org.springframework.stereotype.Repository;

import pl.kwi.db.spring.AbstractDaoSurnames;
import pl.kwi.entities.SurnameEntity;

@Repository
public class SurnameDao extends AbstractDaoSurnames<SurnameEntity> {
	
	public SurnameDao(){
		setClazz(SurnameEntity.class);		
	}
	
}
