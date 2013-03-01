package pl.kwi.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

public class JdbcUtilTest {
	
	
	public final static String PROPS_TEST_FILE_NAME = "project-test.properties";
	public final static String PROP_TEST_DB_URL_USERS = "test.db.url.user";
	public final static String PROP_TEST_DB_URL_NAMES = "test.db.url.name";
	public final static String PROP_TEST_DB_URL_SURNAMES = "test.db.url.surname";
	public final static String PROP_TEST_DB_DRIVER = "test.db.driver";
	public final static String PROP_TEST_DB_USERNAME = "test.db.username";
	public final static String PROP_TEST_DB_PASSWORD = "test.db.password";
	private static Properties props;
	
	
	static {
		
		try {
			props = new Properties();
			props.load(JdbcUtil.class.getResourceAsStream("/" + PROPS_TEST_FILE_NAME));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	public static Connection beginConncetion(String urlDb){
		
		try {
			String dbUrl = props.getProperty(urlDb);
			String dbDriver = props.getProperty(PROP_TEST_DB_DRIVER);
			String dbUser = props.getProperty(PROP_TEST_DB_USERNAME);
			String dbPassword = props.getProperty(PROP_TEST_DB_PASSWORD);
			
			Class.forName(dbDriver).newInstance();
			return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static void finishConnection(Connection conn){
		
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void executeDataFile(String path, Connection conn){
		
		try {
			
			IDatabaseConnection iConn = new DatabaseConnection(conn);
			
			IDataSet dataSet = new FlatXmlDataSetBuilder().build(JdbcUtilTest.class
					.getResourceAsStream(path));

			DatabaseOperation.CLEAN_INSERT.execute(iConn, dataSet);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
