package com.example.hibernate.models;

public enum UserType {
    customer("customer"),
    administrator("administrator"),
    banned("banned"),
    deleted("deleted");

    public final String user_type;

    private UserType(String user_type) {
        this.user_type = user_type;
    }
}
