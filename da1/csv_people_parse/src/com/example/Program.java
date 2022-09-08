package com.example;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        try {
            ArrayList<Person> people = PeopleCSVParser.parseFromFile(System.getenv("JAVA_RESOURCES") + "/da1/people.csv");
            for(var person : people) {
                if(person.getAge() >= 18 && person.getAge() <= 35) {
                    System.out.println(person);
                }
            }
        } catch (Exception ex) {ex.printStackTrace(); }
    }
}
