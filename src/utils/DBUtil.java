package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;

public class DBUtil {
    private static final ComboPooledDataSource cpds;
    static {
        cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl("jdbc:postgresql://john.db.elephantsql.com:5432/gsuesbqg");
        cpds.setUser("gsuesbqg");
        cpds.setPassword("5jv42nzKY9ABoSMCBmKB_PzOJXQh_b5F");
        cpds.setMaxPoolSize(3);
        cpds.setMinPoolSize(1);
    }

    public static Connection getConnection(String methodName) {
        try {
            System.out.println("Connection acquired for method : " + methodName);
            return cpds.getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(Connection conn, String methodName) {
        try {
            if(conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection closed for method : " + methodName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void shutdownCPDS() {
        cpds.close();
        System.out.println("DataBase successfully Closed.....");
    }
}

