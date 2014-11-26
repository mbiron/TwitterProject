package isep.tweetproject.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class Database {
	private static Logger log = Logger.getLogger(Database.class);
	private static String DBURL = "jdbc:mysql://localhost:3306/projectdb";

	private static final BasicDataSource dataSource = new BasicDataSource();

	static {
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(DBURL);
		dataSource.setUsername("root");
		dataSource.setPassword("root");
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
