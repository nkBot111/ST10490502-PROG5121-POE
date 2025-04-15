package com.mycompany.userloginsystem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author RC_Student_lab
 */
public class Login {
  
//  Fields
    private String username;
    private String password;
    private String cellphoneNumber;
    private String firstName;
    private String lastName;
    
//  Constructor
    public Login(String username, String password, String cellphoneNumber, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.cellphoneNumber = cellphoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
//  Username must contain an underscore and must be at least 5 characters in length
    public boolean checkUserName() {
        return username.contains("_") && username.length() >= 5;
    }
    
//  Password must be at least 8 characters long and contain a capital letter, a number and a special character
    public boolean checkPasswordComplexity() {
        return password.length() >= 8  &&
            password.matches(".*[A-Z].*") &&
            password.matches(".*[a-z].*") &&
            password.matches(".*[0-9].*") &&
            password.matches(".*[!@#$%^&*()+].*");
    }
    
//  Cellphone number must start with +27 and be 12 characters long  
    public boolean checkCellphoneNumber() {
        return cellphoneNumber.startsWith("+27") && cellphoneNumber.length() == 12; 
    }
    
//  Registration message based on validations
    public String registeredUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";         
        }
        
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number and a special character.";
        }
        
        if (!checkCellphoneNumber()) {
            return "Cellphone number is incorrectly formatted or does not contain international code. Number must start with +27 and be followed by 9 digits, ensure there are no spaces inbetween the digits";
        }
        return "User registered successfully";
    }
    
//  Login check
    public boolean loginUser(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }
    
//  Login success message
    public String returnLoginStatus(String inputUsername, String inputPassword) {
        if (loginUser(inputUsername, inputPassword)) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        }
        else {
            return "Username or password incorrect, please try again.";
        }
    }
}