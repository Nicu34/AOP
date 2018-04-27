package aspectj;

import org.apache.log4j.Logger;

public aspect EmailServiceTracing {
    private static final Logger LOGGER = Logger.getLogger(EmailServiceTracing.class);

    pointcut tracedSendEmail(): call (* service.EmailService.sendEmail(*));

    after() returning(): tracedSendEmail()  {
        LOGGER.info("Email sent successfully to a possible donor.");
    }

    after() returning(): tracedSendEmail()  {
        LOGGER.info("Failed to send email to a possible donor.");
    }

}