package peaksoft.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Beksultan
 */
public class DatabaseConnection {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5678/postgres"
            );
        } catch (SQLException e) {
            throw new RuntimeException(
                    "cannot connect to database"
            );
        }
    }
}
