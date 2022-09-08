package com.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;

public class Program {
    public static void main(String[] args) {
        try {

            String csv_file_path = System.getenv("JAVA_RESOURCES") + "/da1/authors.csv";
            String[] headers = new String[] {"First name", "Last name", "Born (Year)"};
            /*
            FileWriter out = new FileWriter(csv_file_path);
            CSVPrinter printer = new CSVPrinter(out, CSVFormat.Builder.create().setHeader(headers).build());
            printer.printRecord("Dan", "Simmons", 1949);
            printer.printRecord("Agatha", "Christie", 1890);
            printer.close();
            */
            FileReader in = new FileReader(csv_file_path);
            Iterable<CSVRecord> data = CSVFormat.Builder.create().setHeader(headers).build().parse(in);
            //Iterable<CSVRecord> data = CSVFormat.Builder.create().build().parse(in);

            boolean isFirst = true;
            for(CSVRecord row : data) {
                if(isFirst) { isFirst = false; continue; }
                System.out.println(row.get("First name") + "|" + row.get("Last name") + "|" + row.get("Born (Year)"));
            }
        } catch (Exception ex) { ex.printStackTrace(); }
    }
}
