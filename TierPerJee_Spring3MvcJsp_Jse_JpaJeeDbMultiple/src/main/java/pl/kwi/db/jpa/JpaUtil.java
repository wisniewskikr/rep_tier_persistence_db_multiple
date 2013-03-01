package pl.kwi.db.jpa;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

public class JpaUtil {
	
	
	public final static String PROPS_MAIN_FILE_NAME = "project.properties";
	public final static String PROP_MAIN_USER_TRANSACTION = "main.user.transaction";
	public final static String PROP_MAIN_PERSISTENCE_UNIT_USERS = "main.persistence-unit.users";
	public final static String PROP_MAIN_PERSISTENCE_UNIT_NAMES = "main.persistence-unit.names";
	public final static String PROP_MAIN_PERSISTENCE_UNIT_SURNAMES = "main.persistence-unit.surnames";
	private static Properties props;
	
	
	static {
		
		try {
			props = new Properties();
			props.load(JpaUtil.class.getResourceAsStream("/" + PROPS_MAIN_FILE_NAME));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static JpaTransManagement beginTransaction() {
		
		JpaTransManagement management = new JpaTransManagement();
		
		try {
			
			beginUserTransaction(management);
			management.setEmUsers(openEntityManagers(PROP_MAIN_PERSISTENCE_UNIT_USERS));
			management.setEmNames(openEntityManagers(PROP_MAIN_PERSISTENCE_UNIT_NAMES));
			management.setEmSurnames(openEntityManagers(PROP_MAIN_PERSISTENCE_UNIT_SURNAMES));
			
		} catch (Exception e) {
			management.addException(e);
		}
		
		return management;
		
	}
	
	public static void finishTransaction(JpaTransManagement management) {
		
		try {
			
			finishUserTransaction(management);
			closeEntityManager(management.getEmUsers());
			closeEntityManager(management.getEmNames());
			closeEntityManager(management.getEmSurnames());
			
		} catch (Exception e) {
			management.addException(e);
		}
	
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	private static void commitUserTransaction(JpaTransManagement management){
		
		try {
			
			int status = management.getTx().getStatus();
			
			if(Status.STATUS_ACTIVE == status){
				management.getTx().commit();
			}else if(Status.STATUS_MARKED_ROLLBACK == status){
				management.getTx().rollback();
			}else{
				throw new Exception("Unexpected transaction status: " + status);
			}
			
		} catch (Exception e) {
			management.addException(e);
		}
		
	}
	
	private static void rollbackUserTransaction(JpaTransManagement management){
		
		try {
			
			int status = management.getTx().getStatus();
			
			if(Status.STATUS_ACTIVE == status || Status.STATUS_MARKED_ROLLBACK == status){
				management.getTx().rollback();
			}else{
				throw new Exception("Unexpected transaction status: " + status);
			}
			
		} catch (Exception e) {
			management.addException(e);
		}
		
	}
	
	private static void beginUserTransaction(JpaTransManagement management) throws Exception{
		
		Context ctx = new InitialContext();
		UserTransaction ut = (UserTransaction)ctx.lookup(props.getProperty(PROP_MAIN_USER_TRANSACTION));
		ut.begin();
		management.setTx(ut);
				
	}
	
	private static void finishUserTransaction(JpaTransManagement management) {
		
		if(management.isException()){
			rollbackUserTransaction(management);
		}else {
			commitUserTransaction(management);
		}
	
	}
	
	private static EntityManager openEntityManagers(String persistenceUnit) {
		
		String persistenceUnitName = props.getProperty(persistenceUnit);
		EntityManager em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
		em.joinTransaction();
		return em;
		
		
	}
	
	private static void closeEntityManager(EntityManager em) {
		
		em.close();
		
	}

}
