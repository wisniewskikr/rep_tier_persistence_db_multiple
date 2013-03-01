package pl.kwi.daos;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import pl.kwi.db.jpa.AbstractDaoUsers;
import pl.kwi.entities.UserEntity;

@Repository
public class UserDao extends AbstractDaoUsers<UserEntity> {
	
	public UserDao() {
		setClazz(UserEntity.class);
	}
	
	public UserDao(EntityManager em){
		super(em);
		setClazz(UserEntity.class);
	}
			
}
