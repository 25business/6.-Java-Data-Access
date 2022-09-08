package com.example;

import org.apache.commons.csv.CSVRecord;

public class Person {
    private String first_name;
    private String last_name;
    private int age;

    public static Person fromCSVRecord(CSVRecord person_record) {
        Person p = new Person();
        p.first_name = person_record.get("first_name");
        p.last_name = person_record.get("last_name");
        p.age = Integer.parseInt(person_record.get("age"));
        return p;
    }

    public int getAge() { return this.age; }

    public String toString(){
        return String.format("%s %s, %d", first_name, last_name, age);
    }
}
