package repository;

import model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonRepository {

    private SqlConnection sqlConnection;

    public PersonRepository(SqlConnection sqlConnection) throws SQLException {
        this.sqlConnection = sqlConnection;
        createTableIfNotExists(sqlConnection.getConnection());
    }

    private void createTableIfNotExists(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS persons(\n"
                + "	id integer PRIMARY KEY,\n"
                + "	email text NOT NULL\n"
                + ");";

        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    public void save(Person person) throws SQLException {
        String sql = "INSERT INTO persons(id, email) VALUES(?,?)";
        PreparedStatement preparedStatement = sqlConnection.getConnection() .prepareStatement(sql);

        preparedStatement.setInt(1, person.getEmail().hashCode());
        preparedStatement.setString(2, person.getEmail());

        preparedStatement.execute();
    }
}
