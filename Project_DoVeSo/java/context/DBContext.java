package context;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
	private static String DB_URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=QUANLY_VEDO;"
            + "encode = true; trustServerCertificate = true;";
    private static String USER_NAME = "kiss";
    private static String PASSWORD = "1234";
    
    /**
     * Connect database
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }

    /**
     * roll back connect 
     * @param conn
     */
	public static void rollbackQuietly(Connection conn) {
		// TODO Auto-generated method stub
		try {
			conn.rollback();
		} catch (Exception e) {
		}
	}
	
	/**
	 * Close connect
	 * @param conn
	 */
	public static void closeQuietly(Connection conn) {
		// TODO Auto-generated method stub
		try {
			conn.close();
		} catch (Exception e) {
		}
	}
}
