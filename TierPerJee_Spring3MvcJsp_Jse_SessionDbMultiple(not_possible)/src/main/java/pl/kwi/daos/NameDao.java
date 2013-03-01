package pl.kwi.daos;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import pl.kwi.db.spring.AbstractDaoNames;
import pl.kwi.entities.NameEntity;

@Repository
public class NameDao extends AbstractDaoNames<NameEntity> {
	
	public NameDao(){
		setClazz(NameEntity.class);		
	}
	
}
