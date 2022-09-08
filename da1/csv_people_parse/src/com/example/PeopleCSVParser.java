package com.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PeopleCSVParser {

    public static ArrayList<Person> parseFromFile(String file_path) throws IOException {
        ArrayList<Person> people = new ArrayList<>();
        FileReader in = new FileReader(file_path);
        String[] headers = new String[] {"first_name", "last_name", "age"};
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.builder().setHeader(headers).build().parse(in);
        boolean isFirst = true;
        for(CSVRecord record : records) {
            if(isFirst) { isFirst = false; continue; }
            Person p = Person.fromCSVRecord(record);
            people.add(p);
        }
        return people;
    }
}
