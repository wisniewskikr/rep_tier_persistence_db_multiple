package pl.kwi.db.jpa;

import java.sql.Connection;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.ejb.HibernateEntityManager;
import org.hibernate.engine.spi.SessionImplementor;


public class JpaUtilTest {
	
	public final static String PROPS_TEST_FILE_NAME = "project-test.properties";
	public final static String PROP_TEST_PERSISTENCE_UNIT_USERS = "test.persistence-unit.users";
	public final static String PROP_TEST_PERSISTENCE_UNIT_NAMES = "test.persistence-unit.names";
	public final static String PROP_TEST_PERSISTENCE_UNIT_SURNAMES = "test.persistence-unit.surnames";
	private static Properties props;
	
	
	static {
		
		try {
			props = new Properties();
			props.load(JpaUtil.class.getResourceAsStream("/" + PROPS_TEST_FILE_NAME));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static EntityManager beginEntityManager(String persistenceUnit){
		
		try {
			
			String persistenceUnitName = props.getProperty(persistenceUnit);
			return Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static void finishEntityManager(EntityManager em){
		
		try {
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static EntityTransaction beginTransaction(EntityManager em){
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		return transaction;
	}
	
	public static void commitTransaction(EntityTransaction transaction){
		transaction.commit();
	}
	
	public static void executeDataFile(String path, EntityManager em){
		
		try {
			
			Connection conn = getConnection(em);
			
			IDatabaseConnection iConn = new DatabaseConnection(conn);
			
			IDataSet dataSet = new FlatXmlDataSetBuilder().build(JpaUtilTest.class
					.getResourceAsStream(path));
			
			DatabaseOperation.CLEAN_INSERT.execute(iConn, dataSet);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	private static Connection getConnection(final EntityManager em) {
		  HibernateEntityManager hem = (HibernateEntityManager) em;
		  SessionImplementor sim = (SessionImplementor) hem.getSession();
		  return sim.connection();
	}

}
