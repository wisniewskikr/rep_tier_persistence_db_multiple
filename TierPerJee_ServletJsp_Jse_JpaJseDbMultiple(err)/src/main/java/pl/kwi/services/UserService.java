package pl.kwi.services;

import java.util.ArrayList;
import java.util.List;

import pl.kwi.daos.NameDao;
import pl.kwi.daos.SurnameDao;
import pl.kwi.daos.UserDao;
import pl.kwi.db.jpa.JpaUtil;
import pl.kwi.db.jpa.JpaTransManagement;
import pl.kwi.entities.NameEntity;
import pl.kwi.entities.SurnameEntity;
import pl.kwi.entities.UserEntity;


public class UserService {
		
	public void createUser(UserEntity user) throws Exception{
		
		JpaTransManagement management = JpaUtil.beginTransaction();
		
		try {
			UserDao userDao = new UserDao(management.getEmUsers());
			NameDao nameDao = new NameDao(management.getEmNames());
			SurnameDao surnameDao = new SurnameDao(management.getEmSurnames());
			
			NameEntity nameEntity = user.getNameEntity();			
			nameDao.create(nameEntity);
			user.setNameEntity(nameEntity);
			
			SurnameEntity surnameEntity = user.getSurnameEntity();
			surnameDao.create(surnameEntity);
			user.setSurnameEntity(surnameEntity);
			
			userDao.create(user);
			
		} catch (Exception e) {
			management.addException(e);
		}		
		
		JpaUtil.finishTransaction(management);		
		if(management.isException()){
			throw management.crateJoinedException();
		}
			
	}
	
	public UserEntity readUser(Long id) throws Exception{
		
		JpaTransManagement management = JpaUtil.beginTransaction();
		
		UserEntity user = null;
		
		try {
			UserDao userDao = new UserDao(management.getEmUsers());
			NameDao nameDao = new NameDao(management.getEmNames());
			SurnameDao surnameDao = new SurnameDao(management.getEmSurnames());
			
			user = userDao.read(id);
			
			NameEntity nameEntity = nameDao.read(user.getNameEntity().getId());
			user.setNameEntity(nameEntity);
			
			SurnameEntity surnameEntity = surnameDao.read(user.getSurnameEntity().getId());
			user.setSurnameEntity(surnameEntity);
		} catch (Exception e) {
			management.addException(e);
		}	
		
		JpaUtil.finishTransaction(management);		
		if(management.isException()){
			throw management.crateJoinedException();
		}
		
		return user;
		
	}
	
	public void updateUser(UserEntity user) throws Exception{
		
		JpaTransManagement management = JpaUtil.beginTransaction();
					
		try {
			UserDao userDao = new UserDao(management.getEmUsers());
			NameDao nameDao = new NameDao(management.getEmNames());
			SurnameDao surnameDao = new SurnameDao(management.getEmSurnames());
			
			String name = user.getNameEntity().getName();
			String surname = user.getSurnameEntity().getSurname();
			
			user = userDao.read(user.getId());
			
			Long nameId = user.getNameEntity().getId();
			Long surnameId = user.getSurnameEntity().getId();
			
			nameDao.update(new NameEntity(nameId, name));		
			surnameDao.update(new SurnameEntity(surnameId, surname));
		} catch (Exception e) {
			management.addException(e);
		}
		
		JpaUtil.finishTransaction(management);		
		if(management.isException()){
			throw management.crateJoinedException();
		}
							
	}
	
	public void deleteUser(Long id) throws Exception{
		
		JpaTransManagement management = JpaUtil.beginTransaction();
		
		try {
			UserDao userDao = new UserDao(management.getEmUsers());
			NameDao nameDao = new NameDao(management.getEmNames());
			SurnameDao surnameDao = new SurnameDao(management.getEmSurnames());
			
			UserEntity user = userDao.read(id);
			
			nameDao.delete(user.getNameEntity().getId(), NameEntity.class);
			surnameDao.delete(user.getSurnameEntity().getId(), SurnameEntity.class);
			userDao.delete(id, UserEntity.class);
		} catch (Exception e) {
			management.addException(e);
		}
		
		JpaUtil.finishTransaction(management);		
		if(management.isException()){
			throw management.crateJoinedException();
		}
				
	}
	
	@SuppressWarnings("unchecked")
	public List<UserEntity> getUsers() throws Exception{
		
		JpaTransManagement management = JpaUtil.beginTransaction();
			
		List<UserEntity> users = new ArrayList<UserEntity>();
		try {
			UserDao userDao = new UserDao(management.getEmUsers());
			NameDao nameDao = new NameDao(management.getEmNames());
			SurnameDao surnameDao = new SurnameDao(management.getEmSurnames());
			
			users = (List<UserEntity>)userDao.readAll();
			
			for (UserEntity user : users) {
				
				NameEntity nameEntity = nameDao.read(user.getNameEntity().getId());
				user.setNameEntity(nameEntity);
								
				SurnameEntity surnameEntity = surnameDao.read(user.getSurnameEntity().getId());
				user.setSurnameEntity(surnameEntity);
				
			}
		} catch (Exception e) {
			management.addException(e);
		}
		
		JpaUtil.finishTransaction(management);		
		if(management.isException()){
			throw management.crateJoinedException();
		}
			
		return users;
		
	}

}
