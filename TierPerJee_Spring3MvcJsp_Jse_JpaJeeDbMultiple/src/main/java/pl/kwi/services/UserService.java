package pl.kwi.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.kwi.daos.NameDao;
import pl.kwi.daos.SurnameDao;
import pl.kwi.daos.UserDao;
import pl.kwi.entities.NameEntity;
import pl.kwi.entities.SurnameEntity;
import pl.kwi.entities.UserEntity;


@Service
public class UserService {
	
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private NameDao nameDao;
	@Autowired
	private SurnameDao surnameDao;
	
		
	@Transactional
	public void createUser(UserEntity user){
						
		NameEntity nameEntity = user.getNameEntity();			
		nameDao.create(nameEntity);
		user.setNameEntity(nameEntity);
		
		SurnameEntity surnameEntity = user.getSurnameEntity();
		surnameDao.create(surnameEntity);
		user.setSurnameEntity(surnameEntity);
		
		userDao.create(user);
					
	}
	
	@Transactional
	public UserEntity readUser(Long id){
				
		UserEntity user = null;
		
		user = userDao.read(id);
		
		NameEntity nameEntity = nameDao.read(user.getNameEntity().getId());
		user.setNameEntity(nameEntity);
		
		SurnameEntity surnameEntity = surnameDao.read(user.getSurnameEntity().getId());
		user.setSurnameEntity(surnameEntity);
		
		return user;
		
	}
	
	@Transactional
	public void updateUser(UserEntity user){
		
		String name = user.getNameEntity().getName();
		String surname = user.getSurnameEntity().getSurname();
		
		user = userDao.read(user.getId());
		
		Long nameId = user.getNameEntity().getId();
		Long surnameId = user.getSurnameEntity().getId();
		
		nameDao.update(new NameEntity(nameId, name));		
		surnameDao.update(new SurnameEntity(surnameId, surname));
							
	}
	
	@Transactional
	public void deleteUser(Long id){
		
		UserEntity user = userDao.read(id);
		
		nameDao.delete(user.getNameEntity().getId(), NameEntity.class);
		surnameDao.delete(user.getSurnameEntity().getId(), SurnameEntity.class);
		userDao.delete(id, UserEntity.class);
				
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<UserEntity> getUsers(){
		
		List<UserEntity> users = new ArrayList<UserEntity>();
		
		users = (List<UserEntity>)userDao.readAll();
		
		for (UserEntity user : users) {
			
			NameEntity nameEntity = nameDao.read(user.getNameEntity().getId());
			user.setNameEntity(nameEntity);
							
			SurnameEntity surnameEntity = surnameDao.read(user.getSurnameEntity().getId());
			user.setSurnameEntity(surnameEntity);
			
		}
			
		return users;
		
	}

}
