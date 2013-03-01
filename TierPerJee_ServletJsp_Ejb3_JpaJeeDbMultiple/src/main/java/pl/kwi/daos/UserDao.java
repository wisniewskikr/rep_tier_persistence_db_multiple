package pl.kwi.daos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import pl.kwi.db.jpa.AbstractDaoUsers;
import pl.kwi.entities.UserEntity;

@Stateless
public class UserDao extends AbstractDaoUsers<UserEntity> {
	
	public UserDao() {
		setClazz(UserEntity.class);
	}
	
	public UserDao(EntityManager em){
		super(em);
		setClazz(UserEntity.class);
	}
			
}
