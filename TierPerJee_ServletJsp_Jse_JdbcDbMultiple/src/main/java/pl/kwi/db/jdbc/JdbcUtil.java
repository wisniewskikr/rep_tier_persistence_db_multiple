package pl.kwi.db.jdbc;

import java.sql.Connection;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

public class JdbcUtil {
	
	
	public final static String PROPS_MAIN_FILE_NAME = "project.properties";
	public final static String PROP_MAIN_USER_TRANSACTION = "main.user.transaction";
	public final static String PROP_MAIN_DS_USERS = "main.datasource.user";
	public final static String PROP_MAIN_DS_NAMES = "main.datasource.name";
	public final static String PROP_MAIN_DS_SURNAMES = "main.datasource.surname";
	private static Properties props;
	
	
	static {
		
		try {
			props = new Properties();
			props.load(JdbcUtil.class.getResourceAsStream("/" + PROPS_MAIN_FILE_NAME));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static JdbcTransManagement beginTransaction() {
		
		JdbcTransManagement management = new JdbcTransManagement();
		
		try {
			
			beginUserTransaction(management);
			management.setConnUsers(openConnection(PROP_MAIN_DS_USERS));
			management.setConnNames(openConnection(PROP_MAIN_DS_NAMES));
			management.setConnSurnames(openConnection(PROP_MAIN_DS_SURNAMES));
			
		} catch (Exception e) {
			management.addException(e);
		}
		
		return management;
		
	}
	
	public static void finishTransaction(JdbcTransManagement management) {
		
		try {
			
			finishUserTransaction(management);
			closeConnection(management.getConnUsers());
			closeConnection(management.getConnNames());
			closeConnection(management.getConnSurnames());
			
		} catch (Exception e) {
			management.addException(e);
		}
		
	}
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	private static void commitUserTransaction(JdbcTransManagement management){
		
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
	
	private static void rollbackUserTransaction(JdbcTransManagement management){
		
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
	
	private static void beginUserTransaction(JdbcTransManagement management) throws Exception{
		
		Context ctx = new InitialContext();
		UserTransaction ut = (UserTransaction)ctx.lookup(props.getProperty(PROP_MAIN_USER_TRANSACTION));
		ut.begin();
		management.setTx(ut);
				
	}
	
	private static void finishUserTransaction(JdbcTransManagement management) {
		
		if(management.isException()){
			rollbackUserTransaction(management);
		}else {
			commitUserTransaction(management);
		}
	
	}
	
	private static Connection openConnection(String datasourceJndiName) throws Exception{
					
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx
		    .lookup(props.getProperty(datasourceJndiName));
		Connection conn = ds.getConnection();
		return conn;
				
	}
	
	private static void closeConnection(Connection conn) throws Exception{
		
		if(conn != null){
			conn.close();
		}
		
	}
	
}
