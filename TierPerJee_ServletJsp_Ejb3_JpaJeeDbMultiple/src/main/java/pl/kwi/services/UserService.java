package pl.kwi.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.kwi.daos.NameDao;
import pl.kwi.daos.SurnameDao;
import pl.kwi.daos.UserDao;
import pl.kwi.entities.NameEntity;
import pl.kwi.entities.SurnameEntity;
import pl.kwi.entities.UserEntity;


@Stateless
public class UserService {
	
	
	@EJB
	private UserDao userDao;
	@EJB
	private NameDao nameDao;
	@EJB
	private SurnameDao surnameDao;
	
		
	public void createUser(UserEntity user) throws Exception{
		
		NameEntity nameEntity = user.getNameEntity();			
		nameDao.create(nameEntity);
		user.setNameEntity(nameEntity);
		
		SurnameEntity surnameEntity = user.getSurnameEntity();
		surnameDao.create(surnameEntity);
		user.setSurnameEntity(surnameEntity);
		
		userDao.create(user);
					
	}
	
	public UserEntity readUser(Long id) throws Exception{
		
		
		UserEntity user = null;
		
		user = userDao.read(id);
		
		NameEntity nameEntity = nameDao.read(user.getNameEntity().getId());
		user.setNameEntity(nameEntity);
		
		SurnameEntity surnameEntity = surnameDao.read(user.getSurnameEntity().getId());
		user.setSurnameEntity(surnameEntity);
		
		return user;
		
	}
	
	public void updateUser(UserEntity user) throws Exception{
		
		String name = user.getNameEntity().getName();
		String surname = user.getSurnameEntity().getSurname();
		
		user = userDao.read(user.getId());
		
		Long nameId = user.getNameEntity().getId();
		Long surnameId = user.getSurnameEntity().getId();
		
		nameDao.update(new NameEntity(nameId, name));		
		surnameDao.update(new SurnameEntity(surnameId, surname));
							
	}
	
	public void deleteUser(Long id) throws Exception{
		
		UserEntity user = userDao.read(id);
		
		nameDao.delete(user.getNameEntity().getId(), NameEntity.class);
		surnameDao.delete(user.getSurnameEntity().getId(), SurnameEntity.class);
		userDao.delete(id, UserEntity.class);
				
	}
	
	@SuppressWarnings("unchecked")
	public List<UserEntity> getUsers() throws Exception{
		
			
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
