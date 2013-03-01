package pl.kwi.db.spring;

import java.sql.Connection;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.SessionFactoryUtils;

public class DbUnitUtil {
	
	public static void executeDataFile(String path, SessionFactory sessionFactory) {

		try {
			
			Connection conn = SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			IDatabaseConnection iDbConn = new DatabaseConnection(conn);
			
			IDataSet dataSet = new FlatXmlDataSet(DbUnitUtil.class
					.getResourceAsStream(path));

			
			DatabaseOperation.CLEAN_INSERT.execute(iDbConn, dataSet);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

}
