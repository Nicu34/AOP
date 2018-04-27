package aspectj;

import org.apache.log4j.Logger;

import java.sql.SQLException;

public aspect SqlConnectionTracing {
    private static final Logger LOGGER = Logger.getLogger(SqlConnectionTracing.class);

    pointcut tracedDroppedDatabase(): call (* java.sql.Statement.executeUpdate(String)) && within(repository.SqlConnection);

    pointcut tracedCreatedDatabase(): call (* java.sql.Statement.execute(String)) && within(repository.SqlConnection);

    after() returning(): tracedDroppedDatabase()  {
        LOGGER.info("Database dropped successfully");
    }

    after() throwing(SQLException sqlException): tracedDroppedDatabase()  {
        LOGGER.error("Error dropping database.");
    }

    after() returning(): tracedCreatedDatabase()  {
        LOGGER.info("Database created successfully");
    }

    after() throwing(SQLException sqlException): tracedCreatedDatabase()  {
        LOGGER.error("Cannot create database.");
    }
}