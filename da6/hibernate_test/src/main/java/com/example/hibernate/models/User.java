package com.example.hibernate.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int users_id;
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserType user_type;

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUser_type() {
        return user_type;
    }

    public void setUser_type(UserType user_type) {
        this.user_type = user_type;
    }

    @Override
    public String toString() {
        return "User{" +
                "users_id=" + users_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", user_type=" + user_type +
                '}';
    }
}
