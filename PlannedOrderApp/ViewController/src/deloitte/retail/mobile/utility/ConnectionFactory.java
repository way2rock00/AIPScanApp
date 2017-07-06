package deloitte.retail.mobile.utility;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.Statement;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;

public class ConnectionFactory {
    public ConnectionFactory() {
        super();
    }
    
    protected static Connection conn = null;

    public static Connection getConnection() throws Exception {
        if (conn == null) {
            try {
                String Dir = AdfmfJavaUtilities.getDirectoryPathRoot(AdfmfJavaUtilities.ApplicationDirectory);
                String connStr = "jdbc:sqlite:" + Dir + "/retail.db";
                conn = new SQLite.JDBCDataSource(connStr).getConnection();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return conn;
    }

    public static void commitConnection() {
        try {
            conn.commit();
        } catch (SQLException e) {
        }
    }
    
    public static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static void executeStatement(String statement){
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            stmt.execute(statement);
        } catch (Exception ex) {
            Utility.ApplicationLogger.severe(ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            commitConnection();
            closeConnection();
        }
    }
}
