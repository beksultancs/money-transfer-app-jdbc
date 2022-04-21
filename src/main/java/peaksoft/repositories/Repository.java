package peaksoft.repositories;

import peaksoft.configuration.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Beksultan
 */
public class Repository {

    protected final Connection connection = DatabaseConnection.getConnection();

    protected void customExecute(String sqlQuery) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
