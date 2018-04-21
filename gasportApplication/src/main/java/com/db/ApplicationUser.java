package com.db;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * @author vipul pachauri
 */

@Document(collection = "user")
public class ApplicationUser {

    @Id
    public String id;

    @NotEmpty(message = "User's username must not be blank")
    public String username;

    @NotEmpty(message = "User's password must not be blank")
    public String password;

    @NotEmpty(message = "User's email id must not be blank")
    public String emailId;

    public ApplicationUser(){

    }

    public ApplicationUser(String username) {
        this.username=username;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "username='" + username + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
