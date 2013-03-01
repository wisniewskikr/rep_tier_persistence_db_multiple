package pl.kwi.db.jdbc;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.UserTransaction;

public class JdbcTransManagement {
	
	
	private UserTransaction tx;
	private Connection connUsers;
	private Connection connNames;
	private Connection connSurnames;
	private List<Exception> exceptions = new ArrayList<Exception>();
	
	
	public void addException(Exception e){
		exceptions.add(e);
	}
	
	public boolean isException(){
		
		boolean result = false;
		
		if(!exceptions.isEmpty()){
			result = true;
		}
		
		return result;
		
	}
	
	public Exception crateJoinedException() {
		
		StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    for (Exception exception : exceptions) {
	    	exception.printStackTrace(pw);
		}	    
	    pw.close();
	    return new Exception(sw.toString());
		
	}
	
	public UserTransaction getTx() {
		return tx;
	}
	public void setTx(UserTransaction tx) {
		this.tx = tx;
	}

	public Connection getConnUsers() {
		return connUsers;
	}
	public void setConnUsers(Connection connUsers) {
		this.connUsers = connUsers;
	}

	public Connection getConnNames() {
		return connNames;
	}
	public void setConnNames(Connection connNames) {
		this.connNames = connNames;
	}

	public Connection getConnSurnames() {
		return connSurnames;
	}
	public void setConnSurnames(Connection connSurnames) {
		this.connSurnames = connSurnames;
	}

	public List<Exception> getExceptions() {
		return exceptions;
	}
	public void setExceptions(List<Exception> exceptions) {
		this.exceptions = exceptions;
	}
		

}
