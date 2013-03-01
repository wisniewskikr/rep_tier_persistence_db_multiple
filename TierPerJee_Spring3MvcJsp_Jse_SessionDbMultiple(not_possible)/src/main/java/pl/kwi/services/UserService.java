package pl.kwi.services;

import java.util.List;

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
	public Long createUser(UserEntity user){
		
		NameEntity nameEntity = user.getNameEntity();
		nameDao.create(nameEntity);
		
		SurnameEntity surnameEntity = user.getSurnameEntity();
		surnameDao.create(surnameEntity);
		
		user.setNameEntity(nameEntity);
		user.setSurnameEntity(surnameEntity);
		userDao.create(user);
		return user.getId();
		
	}
	
	@Transactional
	public UserEntity readUser(Long id){
		
		UserEntity user = userDao.findOne(id);
		
		NameEntity nameEntity = nameDao.findOne(user.getNameId());
		user.setNameEntity(nameEntity);
		
		SurnameEntity surnameEntity = surnameDao.findOne(user.getSurnameId());
		user.setSurnameEntity(surnameEntity);
		
		return user;
		
	}
	
	@Transactional
	public void updateUser(UserEntity user){
		
		String newName = user.getNameEntity().getName();
		String newSurname = user.getSurnameEntity().getSurname();
		
		user = userDao.findOne(user.getId());
		
		NameEntity nameEntity = user.getNameEntity();
		nameEntity.setName(newName);
		nameDao.update(nameEntity);
		
		SurnameEntity surnameEntity = user.getSurnameEntity();
		surnameEntity.setSurname(newSurname);
		surnameDao.update(surnameEntity);
		
		userDao.update(user);
		
	}
	
	@Transactional
	public void deleteUser(UserEntity user){
		
		user = userDao.findOne(user.getId());
		
		nameDao.deleteById(user.getNameId());
		
		surnameDao.deleteById(user.getSurnameId());
		
		userDao.delete(user);
		
	}
	
	@Transactional
	public List<UserEntity> getUserList(){
		
		List<UserEntity> users = userDao.findAll();
		
		for (UserEntity user : users) {
			
			NameEntity nameEntity = nameDao.findOne(user.getNameId());
			user.setNameEntity(nameEntity);
			
			SurnameEntity surnameEntity = surnameDao.findOne(user.getSurnameId());
			user.setSurnameEntity(surnameEntity);
			
		}
		
		return users;
		
	}

}
