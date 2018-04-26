package service;

import model.Person;
import model.RHEnum;
import model.SanguinGroupEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BloodDonors {

    private static final Logger LOGGER = Logger.getLogger(BloodDonors.class.getName());

    private SanguinGroupEnum sanguinGroupEnum;

    private RHEnum rhEnum;

    private List<Person> possibleDonors = new ArrayList<>();

    private String messageToBeReceived;

    private EmailService emailService = new EmailService();

    public BloodDonors(SanguinGroupEnum sanguinGroupEnum, RHEnum rhEnum) {
        this.sanguinGroupEnum = sanguinGroupEnum;
        this.rhEnum = rhEnum;
    }

    // add observer
    public void addPossibleDonor(Person person) {
        possibleDonors.add(person);
        LOGGER.log(Level.INFO, "Person with id " + person.getId() + " added successfully as possible donor with group " + sanguinGroupEnum + " and " + rhEnum + " RH");
    }

    // notify observers
    public void setMessageToBeReceived(String message) {
        LOGGER.log(Level.INFO, "Starting notify possible observers with sanguing group " + sanguinGroupEnum + " and " + rhEnum + " RH");
        this.messageToBeReceived = message;

        possibleDonors.forEach(person -> emailService.sendEmail(person.getEmail(), message));
    }

}
