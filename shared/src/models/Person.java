package models;

import util.ParentGenerationHelper;
import util.Random;

import java.util.Objects;

/**
 * a person in that contains relations to other persons in database containing family history
 */
public class Person {

    private String personID;
    private String associatedUsername;
    private String firstName;
    private String lastName;
    private String gender;
    private String fatherID; // nullable
    private String motherID; // nullable
    private String spouseID; // nullable

    /**
     * constructor used to instantiated new Person object with all params included
     *
     * @param personID
     * @param associatedUsername
     * @param firstName
     * @param lastName
     * @param gender
     * @param fatherID
     * @param motherID
     * @param spouseID
     */
    public Person(String personID, String associatedUsername, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }


    /**
     * constructor used to instantiated new Person object with the nullable parameters excluded
     *
     * @param personID
     * @param associatedUsername
     * @param firstName
     * @param lastName
     * @param gender
     */
    public Person(String personID, String associatedUsername, String firstName, String lastName, String gender) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    /**
     * Defualt Person constructor: used for providing dummy data
     */
    public Person() {
    }

    public static Person generateRandomPerson(String associatedUsername, Boolean isMale, String personID) {
        String gender;
        if (isMale) {
            gender = "m";
        } else {
            gender = "f";
        }
        return new Person(
                personID,
                associatedUsername,
                Random.getRandomFirstName(isMale),
                Random.getRandomLastName(),
                gender
        );
    }

    public void generateParent(String parentID, String spouseID, int generation, Boolean isFather, ParentGenerationHelper parentGenerationHelper, Event marriageEvent) {



        if (generation < parentGenerationHelper.getTargetNumGenerations()) {

            // creating parent
            Person parent = generateRandomPerson(associatedUsername, isFather, parentID);
            parentGenerationHelper.addPerson(parent);

            if (spouseID != null) {
                parent.setSpouseID(spouseID);
            }

            // linking parent and child
            if (isFather) {
                this.setFatherID(parentID);
            } else {
                this.setMotherID(parentID);
            }

            // generate events
            parentGenerationHelper.addEvent(Event.generateRandomEvent(parent, marriageEvent.getYear() - 20, Event.EventType.birth));
            parentGenerationHelper.addEvent(Event.generateSyncMarriageEvent(parent, marriageEvent.getYear(), marriageEvent.getLatitude(), marriageEvent.getLongitude(), marriageEvent.getCountry(), marriageEvent.getCity()));
            parentGenerationHelper.addEvent(Event.generateRandomEvent(parent, marriageEvent.getYear() + 70, Event.EventType.death));

            // mother father marriage sync
            Event parentMarriageEvent = Event.generateRandomEvent(this, marriageEvent.getYear() - 20, Event.EventType.marriage);
            String fatherID = Random.generateUUID();
            String motherID = Random.generateUUID();

            // recursive
            parent.generateParent(fatherID, motherID, generation + 1, true, parentGenerationHelper, parentMarriageEvent);
            parent.generateParent(motherID, fatherID, generation + 1, false, parentGenerationHelper, parentMarriageEvent);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(personID, person.personID) && Objects.equals(associatedUsername, person.associatedUsername) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(gender, person.gender) && Objects.equals(fatherID, person.fatherID) && Objects.equals(motherID, person.motherID) && Objects.equals(spouseID, person.spouseID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personID, associatedUsername, firstName, lastName, gender, fatherID, motherID, spouseID);
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }
}


