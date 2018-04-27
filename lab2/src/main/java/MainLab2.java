import model.Person;
import model.RHEnum;
import model.SanguinGroupEnum;
import org.apache.log4j.BasicConfigurator;
import repository.PersonRepository;
import repository.SqlConnection;
import service.BloodDonors;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainLab2 {

    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(MainLab2.class.getName());

        try {
            BasicConfigurator.configure();

            SqlConnection sqlConnection = new SqlConnection();
            PersonRepository personRepository = new PersonRepository(sqlConnection);

            Person person1 = mockPerson("muresannikolai@gmail.com");
            Person person2 = mockPerson("spe3d_pyr0@yahoo.com");

            personRepository.save(person1);
            personRepository.save(person2);

            BloodDonors bloodDonors = new BloodDonors(SanguinGroupEnum.O, RHEnum.POSITIVE);

            bloodDonors.addPossibleDonor(person1);
            bloodDonors.addPossibleDonor(person2);

            bloodDonors.setMessageToBeReceived("We need blood.");
            bloodDonors.setMessageToBeReceived("We need again blood.");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error manipulating sql connection", e);
        }
    }

    private static Person mockPerson(String email) {
        Person person = new Person();

        person.setId(email.hashCode());
        person.setEmail(email);

        return person;
    }
}
