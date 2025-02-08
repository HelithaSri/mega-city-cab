package lk.cab.manager.megacitycab.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    // Singleton instance
    private static DbConnection instance;

    // Get DATABASE_URL from environment variable
    private static final String DATABASE_URL = System.getenv("ICBT_DATABASE_URL");

    private DbConnection() {}

    // Singleton instance getter
    public static DbConnection getInstance() {
        if (instance == null) {
            synchronized (DbConnection.class) {
                if (instance == null) {
                    instance = new DbConnection();
                }
            }
        }
        return instance;
    }

    // Get a database connection
    public Connection getConnection() throws SQLException {
        if (DATABASE_URL == null || DATABASE_URL.isEmpty()) {
            throw new SQLException("DATABASE_URL is not set in environment variables");
        }
        return DriverManager.getConnection(DATABASE_URL);
    }
}