package service;

import model.Person;
import model.RHEnum;
import model.SanguinGroupEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BloodDonors {

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
    }

    // notify observers
    public void setMessageToBeReceived(String message) {
        this.messageToBeReceived = message;

        possibleDonors.forEach(person -> emailService.sendEmail(person.getEmail(), message));
    }

}
