package aspectj;

import org.apache.log4j.Logger;

import java.sql.SQLException;

public aspect BloodDonorsTracing {
    private static final Logger LOGGER = Logger.getLogger(BloodDonorsTracing.class);

    pointcut tracedAddPossibleDonor(): call (* service.BloodDonors.addPossibleDonor(model.Person));

    pointcut tracedSetMessage(): call (* service.BloodDonors.setMessageToBeReceived(String));

    after() returning(): tracedAddPossibleDonor()  {
        LOGGER.info("Possible donor added successfully.");
    }

    after() returning(): tracedSetMessage()  {
        LOGGER.info("Starting notify possible donors (observers) via email.");
    }

}