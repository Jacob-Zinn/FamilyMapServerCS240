package util;

import java.util.UUID;

public class Random {

    public static int getRandomInt(int start, int range) {
        // Obtain a number between [0 - (range - 1)].
        java.util.Random rand=new java.util.Random();
        int i=rand.nextInt(range);
        return i + start;
    }

    public static String getRandomFirstName(Boolean isMale) {
        if (isMale) {
            String[] boyNames={"Liam", "Noah", "Oliver", "Elijah", "William", "James", "Benjamin", "Lucas", "Henry", "Alexander", "Mason", "Michael", "Ethan", "Daniel", "Jacob", "Logan", "Jackson", "Levi", "Sebastian", "Mateo", "Jack", "Owen", "Theodore", "Aiden", "Samuel", "Joseph", "John", "David", "Wyatt", "Matthew", "Luke", "Asher", "Carter", "Julian", "Grayson", "Leo", "Jayden", "Gabriel", "Isaac", "Lincoln", "Anthony", "Hudson", "Dylan", "Ezra", "Thomas", "Charles" };
            return boyNames[Random.getRandomInt(0, boyNames.length)];
        } else {
            String[] girlNames={"Violet", "Nova", "Hannah", "Emilia", "Zoe", "Stella", "Everly", "Isla", "Leah", "Lillian", "Addison", "Willow", "Lucy", "Paisley", "Natalie", "Naomi", "Eliana", "Brooklyn", "Elena", "Aubrey", "Claire", "Ivy", "Kinsley", "Audrey", "Maya", "Genesis", "Skylar", "Bella", "Aaliyah", "Madelyn", "Savannah", "Anna", "Delilah", "Serenity", "Caroline", "Kennedy", "Valentina", "Ruby", "Sophie", "Alice", "Gabriella", "Sadie", "Ariana", "Allison", "Hailey", "Autumn", "Nevaeh", "Natalia", "Quinn", "Josephine", "Sarah", "Cora", "Emery", "Samantha", "Piper", "Leilani", "Eva", "Everleigh", "Madeline", "Lydia", "Jade", "Peyton", "Brielle", "Adeline", "Vivian", "Rylee", "Clara", "Raelynn", "Melanie", "Melody", "Julia", "Athena", "Maria", "Liliana", "Hadley" };
            return girlNames[Random.getRandomInt(0, girlNames.length)];
        }
    }

    public static String getRandomLastName() {
        String[] lastNames = {"Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas", "Taylor", "Moore", "Jackson", "Martin", "Lee", "Perez", "Thompson", "White", "Harris", "Sanchez", "Clark"};
        return lastNames[Random.getRandomInt(0, lastNames.length)];
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
