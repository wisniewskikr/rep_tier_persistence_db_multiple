package pl.kwi.services;

import java.util.ArrayList;
import java.util.List;

import pl.kwi.daos.NameDao;
import pl.kwi.daos.SurnameDao;
import pl.kwi.daos.UserDao;
import pl.kwi.db.jdbc.JdbcTransManagement;
import pl.kwi.db.jdbc.JdbcUtil;
import pl.kwi.entities.NameEntity;
import pl.kwi.entities.SurnameEntity;
import pl.kwi.entities.UserEntity;


public class UserService {
	
		
	public void createUser(UserEntity user) throws Exception{
				
		JdbcTransManagement management = JdbcUtil.beginTransaction();
		
		try{
			
			NameDao nameDao = new NameDao(management.getConnNames());
			SurnameDao surnameDao = new SurnameDao(management.getConnSurnames());
			UserDao userDao = new UserDao(management.getConnUsers());
			
			nameDao.createName(user.getNameEntity());
			
			surnameDao.createSurname(user.getSurnameEntity());
					
			userDao.createUser(user);
			
		}catch (Exception e){
			management.addException(e);
		}
			
		JdbcUtil.finishTransaction(management);
		if(management.isException()){
			throw management.crateJoinedException();
		}
		
	}
	
	public UserEntity readUser(Long id) throws Exception{
		
		UserEntity user = null;		
		JdbcTransManagement management = JdbcUtil.beginTransaction();
		
		try{
			
			UserDao userDao = new UserDao(management.getConnUsers());
			NameDao nameDao = new NameDao(management.getConnNames());
			SurnameDao surnameDao = new SurnameDao(management.getConnSurnames());
			
			user = userDao.readUser(id);
			
			NameEntity nameEntity = nameDao.readName(user.getNameEntity().getId());
			user.setNameEntity(nameEntity);
			
			SurnameEntity surnameEntity = surnameDao.readSurname(user.getSurnameEntity().getId());
			user.setSurnameEntity(surnameEntity);			
			
		}catch (Exception e){
			management.addException(e);
		}
			
		JdbcUtil.finishTransaction(management);
		if(management.isException()){
			throw management.crateJoinedException();
		}
		
		return user;
		
	}
	
	public void updateUser(UserEntity user) throws Exception{
		
		JdbcTransManagement management = JdbcUtil.beginTransaction();
		
		try{
			
			UserDao userDao = new UserDao(management.getConnUsers());
			NameDao nameDao = new NameDao(management.getConnNames());
			SurnameDao surnameDao = new SurnameDao(management.getConnSurnames());
			
			String name = user.getNameEntity().getName();
			String surname = user.getSurnameEntity().getSurname();
			
			user = userDao.readUser(user.getId());
			
			Long nameId = user.getNameEntity().getId();
			Long surnameId = user.getSurnameEntity().getId();
			
			nameDao.updateName(new NameEntity(nameId, name));
			
			surnameDao.updateSurname(new SurnameEntity(surnameId, surname));					
			
		}catch (Exception e){
			management.addException(e);
		}
			
		JdbcUtil.finishTransaction(management);
		if(management.isException()){
			throw management.crateJoinedException();
		}
						
	}
	
	public void deleteUser(Long id) throws Exception{
		
		JdbcTransManagement management = JdbcUtil.beginTransaction();
		
		try{
			
			UserDao userDao = new UserDao(management.getConnUsers());
			NameDao nameDao = new NameDao(management.getConnNames());
			SurnameDao surnameDao = new SurnameDao(management.getConnSurnames());
			
			UserEntity user = userDao.readUser(id);
			
			nameDao.deleteName(user.getNameEntity().getId());
			
			surnameDao.deleteSurname(user.getSurnameEntity().getId());
					
			userDao.deleteUser(id);
			
		}catch (Exception e){
			management.addException(e);
		}
			
		JdbcUtil.finishTransaction(management);
		if(management.isException()){
			throw management.crateJoinedException();
		}
				
	}
	
	public List<UserEntity> getUsers() throws Exception{
		
		List<UserEntity> users = new ArrayList<UserEntity>();
		JdbcTransManagement management = JdbcUtil.beginTransaction();
		
		try{
			
			UserDao userDao = new UserDao(management.getConnUsers());
			NameDao nameDao = new NameDao(management.getConnNames());
			SurnameDao surnameDao = new SurnameDao(management.getConnSurnames());
			
			users = userDao.getUsers();
			
			for (UserEntity user : users) {
				
				NameEntity nameEntity = nameDao.readName(user.getNameEntity().getId());
				user.setNameEntity(nameEntity);
								
				SurnameEntity surnameEntity = surnameDao.readSurname(user.getSurnameEntity().getId());
				user.setSurnameEntity(surnameEntity);
				
			}
			
		}catch (Exception e){
			management.addException(e);
		}
			
		JdbcUtil.finishTransaction(management);
		if(management.isException()){
			throw management.crateJoinedException();
		}
		
		return users;
		
	}

}
