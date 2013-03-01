package pl.kwi.daos;

import java.sql.Connection;
import java.sql.Statement;

import pl.kwi.db.jdbc.AbstractDao;
import pl.kwi.entities.NameEntity;

public class NameDao extends AbstractDao {
	
	
	public NameDao(Connection conn){
		super(conn);
	}
	
	
	public void createName(NameEntity name) throws Exception{
				
		String sql = "INSERT INTO names(name) VALUES(?)";
		ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, name.getName());
	    ps.executeUpdate();
	    rs = ps.getGeneratedKeys();
	    
	    while(rs.next()){
	    	name.setId(rs.getLong(1));
	    }
	    
		rs.close();
		ps.close();
	    
	}
	
	public NameEntity readName(Long id) throws Exception{
		
		NameEntity name = null;
		
		String sql = "SELECT id, name FROM names WHERE id = ?";
		ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		rs = ps.executeQuery();
		
		while(rs.next()){
			name = new NameEntity();
			name.setId(rs.getLong(1));
			name.setName(rs.getString(2));
		}
		
		rs.close();
		ps.close();
		
		return name;
		
	}
	
	public void updateName(NameEntity name) throws Exception{
		
		String sql = "UPDATE names SET name = ? WHERE id = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, name.getName());
		ps.setLong(2, name.getId());
	    ps.executeUpdate();
	    ps.close();
		
	}
	
	public void deleteName(Long id) throws Exception {
		
		String sql = "DELETE FROM names WHERE id = ?";
		ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
	    ps.executeUpdate();
	    ps.close();
		
	}

	
}
