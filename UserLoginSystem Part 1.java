/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.userloginsystem;

/**
 *
 * @author RC_Student_lab
 */

import java.util.Scanner;

public class UserLoginSystem {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;
        
        // Show user options (register or login)
        do {
            System.out.println("=== User Login System ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
//  Handle registration process
        System.out.println("\nYou chose to Register.");
        handleRegistration(scanner);
        break;
                
        case 2:
//  Handle login process
        System.out.println("\nYou chose to Login.");
        handleLogin(scanner);
        break;
                
        case 3:
        System.out.println("Exiting the system.");
        break;
                
        default:
        System.out.println("Invalid option. Please try again.");
        break;
            }
        } while (option != 3);

        scanner.close();
    }

//  Method for handling registration
    private static void handleRegistration(Scanner scanner) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        
        System.out.print("Enter username: ");
        String loginUsername = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine();
        
        System.out.print("Cellphone number (with +27): ");
        String cellphoneNumber = scanner.nextLine();
        
//  Create the Login object to verify the credentials
        Login login = new Login(loginUsername, loginPassword, "", "", "");
        String registrationResult = login.registeredUser();
        
        while (!"User registered successfully".equals(registrationResult)) {
            System.out.println(registrationResult);
            System.out.print("Re-enter username: ");
            loginUsername = scanner.nextLine();
            System.out.print("Re-enter password: ");
            loginPassword = scanner.nextLine();
            System.out.print("Re-enter cellphone number: ");
            cellphoneNumber = scanner.nextLine();
            
            login = new Login(loginUsername, loginPassword, cellphoneNumber, firstName, lastName);
            registrationResult = login.registeredUser();           
        }
        System.out.println("Registration successful!");
    }

    private static void handleLogin(Scanner scanner) {
        System.out.print("Enter username: ");
        String loginUsername = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine();
        
        System.out.print("Enter cellphone number: ");
        String cellphoneNumber = scanner.nextLine();
        
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
           
        Login login = new Login(loginUsername, loginPassword, cellphoneNumber, firstName, lastName);
        System.out.println(login.returnLoginStatus(loginUsername, loginPassword));
    }
}