package pl.kwi.daos;

import java.sql.Connection;
import java.sql.Statement;

import pl.kwi.db.jdbc.AbstractDao;
import pl.kwi.entities.SurnameEntity;

public class SurnameDao extends AbstractDao {
	
	
	public SurnameDao(Connection conn){
		super(conn);
	}
	
	
	public void createSurname(SurnameEntity surname) throws Exception{
				
		String sql = "INSERT INTO surnames(surname) VALUES(?)";
		ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, surname.getSurname());
	    ps.executeUpdate();
	    rs = ps.getGeneratedKeys();
	    
	    while(rs.next()){
	    	surname.setId(rs.getLong(1));
	    }
	    
	    rs.close();
		ps.close();
	    
	}
	
	public SurnameEntity readSurname(Long id) throws Exception{
		
		SurnameEntity surname = null;
		
		String sql = "SELECT id, surname FROM surnames WHERE id = ?";
		ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		rs = ps.executeQuery();
		
		while(rs.next()){
			surname = new SurnameEntity();
			surname.setId(rs.getLong(1));
			surname.setSurname(rs.getString(2));
		}
		
		rs.close();
		ps.close();
		
		return surname;
		
	}
	
	public void updateSurname(SurnameEntity surname) throws Exception{
		
		String sql = "UPDATE surnames SET surname = ? WHERE id = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, surname.getSurname());
		ps.setLong(2, surname.getId());
	    ps.executeUpdate();
		ps.close();
		
	}
	
	public void deleteSurname(Long id) throws Exception {
		
		String sql = "DELETE FROM surnames WHERE id = ?";
		ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
	    ps.executeUpdate();
		ps.close();
		
	}

	
}
