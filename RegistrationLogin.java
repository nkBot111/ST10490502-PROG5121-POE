package com.mycompany.userloginsystem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RC_Student_lab
 */
import com.mycompany.userloginsystem.Login;

import java.util.Scanner;

public class RegistrationLogin {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
//  Registration
        System.out.println("=== Registration ===");
        
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        
        System.out.print("Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        System.out.print("Cellphone number (with +27): ");
        String cellphoneNumber = scanner.nextLine();
        
        String registrationResult;
        Login login;
        
//  Repeat registration until successful
        do {    
            login = new Login(username, password, cellphoneNumber, firstName, lastName);
            registrationResult = login.registeredUser();
            System.out.println(registrationResult);
        } while (!"User registered successfully".equals(registrationResult));
    
//  Login
        System.out.println("\n== Login ==");
        
        System.out.print("Enter username: ");
        String loginUsername = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine();
        
//  Output login status
        System.out.println(login.returnLoginStatus(loginUsername, loginPassword));
        
//  After successful login
if (login.returnLoginStatus(loginUsername, loginPassword).contains("Welcome")) {
    System.out.println("You are now logged in. Do you want to perform more actions?");
    // Add additional options or exit logic here
}
        scanner.close();
    }

    static void registeredUser(Scanner scanner) {
    }
}