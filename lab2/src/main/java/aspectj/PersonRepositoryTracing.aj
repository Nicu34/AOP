package aspectj;

import org.apache.log4j.Logger;

import java.sql.SQLException;

public aspect PersonRepositoryTracing {
    private static final Logger LOGGER = Logger.getLogger(PersonRepositoryTracing.class);

    pointcut tracedCreateTable(): call (* repository.PersonRepository.createTableIfNotExists(java.sql.Connection));

    pointcut tracedSavePerson(): call (void repository.PersonRepository.save(model.Person)) && within(MainLab2);

    after() returning(): tracedCreateTable()  {
        LOGGER.info("Table persons created if not exists successfully");
    }

    after() throwing(SQLException sqlException): tracedCreateTable()  {
        LOGGER.error("Error creating persons table.");
    }

    after() returning(): tracedSavePerson()  {
        LOGGER.info("Person saved successfully into database");
    }

    after() throwing (SQLException sqlException): tracedSavePerson(){
        LOGGER.error("Error saving person into database.");
    }
}