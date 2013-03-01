package pl.kwi.daos;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.kwi.db.jdbc.AbstractDao;
import pl.kwi.entities.NameEntity;
import pl.kwi.entities.SurnameEntity;
import pl.kwi.entities.UserEntity;

public class UserDao extends AbstractDao {
	
	
	public UserDao(Connection conn){
		super(conn);
	}
	
	
	public void createUser(UserEntity user) throws Exception{
				
		String sql = "INSERT INTO users(name_id, surname_id) VALUES(?, ?)";
		ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setLong(1, user.getNameEntity().getId());
		ps.setLong(2, user.getSurnameEntity().getId());
	    ps.executeUpdate();
	    rs = ps.getGeneratedKeys();
	    
	    while(rs.next()){
	    	user.setId(rs.getLong(1));
	    }
	    
	    rs.close();
		ps.close();
	    
	}
	
	public UserEntity readUser(Long id) throws Exception{
		
		UserEntity user = null;
		
		String sql = "SELECT id, name_id, surname_id FROM users WHERE id = ?";
		ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		rs = ps.executeQuery();
		
		while(rs.next()){
			user = new UserEntity();
			user.setId(rs.getLong(1));
			user.setNameEntity(new NameEntity(rs.getLong(2), null));
			user.setSurnameEntity(new SurnameEntity(rs.getLong(3), null));
		}
		
		rs.close();
		ps.close();
		
		return user;
		
	}
	
	public void updateUser(UserEntity user) throws Exception{
		
		String sql = "UPDATE users SET name_id = ?, surname_id = ? WHERE id = ?";
		ps = conn.prepareStatement(sql);
		ps.setLong(1, user.getNameEntity().getId());
		ps.setLong(2, user.getSurnameEntity().getId());
		ps.setLong(3, user.getId());
	    ps.executeUpdate();
		ps.close();
		
	}
	
	public void deleteUser(Long id) throws Exception {
		
		String sql = "DELETE FROM users WHERE id = ?";
		ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
	    ps.executeUpdate();
		ps.close();
		
	}
	
	public List<UserEntity> getUsers() throws Exception{
		
		List<UserEntity> users = new ArrayList<UserEntity>();
		UserEntity user = null;
		
		String sql = "SELECT id, name_id, surname_id FROM users";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		
		while(rs.next()){
			user = new UserEntity();
			user.setId(rs.getLong(1));
			user.setNameEntity(new NameEntity(rs.getLong(2), null));
			user.setSurnameEntity(new SurnameEntity(rs.getLong(3), null));
			users.add(user);
		}
		
		rs.close();
		ps.close();
		
		return users;
		
	}

}
