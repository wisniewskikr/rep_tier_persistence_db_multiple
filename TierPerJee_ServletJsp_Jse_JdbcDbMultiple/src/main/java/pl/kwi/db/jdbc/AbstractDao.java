package pl.kwi.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class AbstractDao {
	
	protected Connection conn = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;
	
	
	public AbstractDao(Connection conn){
		this.conn = conn;
	}


	public Connection getConn() {
		return conn;
	}
	

}
