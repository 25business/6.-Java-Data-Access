package com.example;

import de.vandermeer.asciitable.AsciiTable;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class Program {
    public static void main(String[] args) {
        try {
            TextIO textIO = TextIoFactory.getTextIO();
            TextTerminal terminal = textIO.getTextTerminal();

            //String username = textIO.newStringInputReader().read("Enter username:");
            //String password = textIO.newStringInputReader().withInputMasking(true).read("Enter password:");

            AsciiTable at = new AsciiTable();
            at.addRule();
            at.addRow("First name", "Last name", "Age");
            at.addRule();
            at.addRow("John", "Smith", 27);
            at.addRule();

            terminal.println(at.render());

            //terminal.printf("You have entered\nUsername: %s\nPassword: %s", username, password);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
