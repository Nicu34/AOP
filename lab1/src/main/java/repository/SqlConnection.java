package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlConnection {

    private static final Logger LOGGER = Logger.getLogger(SqlConnection.class.getName());

    private Connection connection;

    private static final String DB_NAME = "lab1AOP";

    private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME;

    private static final String USERNAME = "root";

    private static final String PASSWORD = "";

    public SqlConnection() throws SQLException {
        connection = getConnection();
        Statement statement = connection.createStatement();

        String dropSql = "DROP DATABASE " + DB_NAME;
        statement.executeUpdate(dropSql);
        LOGGER.log(Level.INFO, "Dropped database" + DB_NAME + "successfully");

        String createSql = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
        statement.execute(createSql);
        LOGGER.log(Level.INFO, "Created database" + DB_NAME + "successfully");
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}