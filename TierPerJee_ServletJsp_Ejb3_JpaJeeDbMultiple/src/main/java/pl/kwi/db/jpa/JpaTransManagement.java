package pl.kwi.db.jpa;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

public class JpaTransManagement {
	
	
	private UserTransaction tx;
	private EntityManager emUsers;
	private EntityManager emNames;
	private EntityManager emSurnames;
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

	public EntityManager getEmUsers() {
		return emUsers;
	}
	public void setEmUsers(EntityManager emUsers) {
		this.emUsers = emUsers;
	}

	public EntityManager getEmNames() {
		return emNames;
	}
	public void setEmNames(EntityManager emNames) {
		this.emNames = emNames;
	}

	public EntityManager getEmSurnames() {
		return emSurnames;
	}
	public void setEmSurnames(EntityManager emSurnames) {
		this.emSurnames = emSurnames;
	}
	

}
