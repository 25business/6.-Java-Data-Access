package com.example.ui;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class Application {
    private static TextIO input = null;
    private static TextTerminal output = null;

    public static TextIO input() {
        if(input == null) input = TextIoFactory.getTextIO();
        return input;
    }
    public static TextTerminal terminal() {
        if(output == null) {
            output = input().getTextTerminal();
            output.getProperties().put("input.font.size", 20);
            output.getProperties().put("prompt.font.size", 20);
        }
        return output;
    }

    public static void main_menu() {
        terminal().println("""
                ---- Contact Application ---- 
                [1] Show contacts
                [2] Add new contact
                [3] Update contact
                [4] Delete contact
                [Q] Quit application""");
    }
    public static void main_menu_actions() {
        String action = input().newStringInputReader().read("Your choice:");
        if(action.toUpperCase().equals("Q")) System.exit(0);
        else if(action.equals("1")) terminal().println(ContactsUI.view_all());
        else if(action.equals("2")) terminal().println(ContactsUI.new_contact(input()));
        else if(action.equals("3")) terminal().println(ContactsUI.update(input()));
    }

    public static void main_loop() {
        while(true) {
            main_menu();
            main_menu_actions();
        }
    }
}
