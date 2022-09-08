package com.example;

import com.example.ui.Application;

public class Program {
    public static void main(String[] args) {
        try {
            Application.main_loop();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
