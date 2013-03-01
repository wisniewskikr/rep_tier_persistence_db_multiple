package pl.kwi.daos;

import org.springframework.stereotype.Repository;

import pl.kwi.db.spring.AbstractDaoUsers;
import pl.kwi.entities.UserEntity;

@Repository
public class UserDao extends AbstractDaoUsers<UserEntity> {
		
	public UserDao(){
		setClazz(UserEntity.class);		
	}
	
}
