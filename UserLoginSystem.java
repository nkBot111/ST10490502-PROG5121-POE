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

    private static final Scanner scanner = new Scanner(System.in);

    private static final String[] usernames = {"admin", "user1", "guest"};
    private static final String[] passwords = {"admin123", "user123", "guest123"};

    private static Message[] sentMessages = new Message[100];
    private static Message[] storedMessages = new Message[100];
    private static Message[] discardedMessages = new Message[100];
    
    private static String[] sentHashes = new String[100];
    private static String[] storedHashes = new String[100];
    private static String[] discardedHashes = new String[100];
    
    private static String[] sentMessageIDs = new String [100];
    private static String[] storedMessageIDs = new String[100];
    private static String [] discardedMessageIDs = new String[100];
    
    private static int sentMessageCount = 0;
    private static int storedMessageCount = 0;
    private static int discardedMessageCount = 0;

//      Main method: Entry point of the application
    
    public static void main(String[] args) {
        System.out.println("Welcome to the User Login System");

        if (!login()) {
            System.out.println("Too many failed attempts. Exiting.");
            return;
        }

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Send Message");
            System.out.println("2. Store Message");
            System.out.println("3. Discard Message");
            System.out.println("4. View Message Details");
            System.out.println("5. View Stored Messages");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 0) {
                System.out.println("Exiting program...");
                break;
            }

            if (choice >= 1 && choice <= 3) {
                System.out.print("Enter recipient cell number: ");
                String cell = scanner.nextLine();
                System.out.print("Enter message text: ");
                String text = scanner.nextLine();

                Message message = new Message(cell, text);

                if (!message.checkRecipientCell()) {
                    System.out.println("Invalid recipient number format.");
                    continue;
                }

                String result = message.SentMessage(choice);
                System.out.println(result);

                if (choice == 1) {
                    
                    sentMessages[sentMessageCount] = message;
                    sentHashes[sentMessageCount] = message.createMessageHash();
                    sentMessageIDs[sentMessageCount] = message.generateMessageID();
                    sentMessageCount++;
                    
                } else if (choice == 2) {
                    
                    storedMessages[storedMessageCount] = message;
                    storedHashes[storedMessageCount] = message.createMessageHash();
                    storedMessageIDs[storedMessageCount] = message.generateMessageID();
                    storedMessageCount++;
                    
                } else if (choice == 3) {
                    
                    discardedMessages[discardedMessageCount] = message;
                    discardedHashes[discardedMessageCount] = message.createMessageHash();
                    discardedMessageIDs[discardedMessageCount] = message.generateMessageID();
                    discardedMessageCount++;
                }
                
            } else if (choice == 4) {
                
                   System.out.println("\nSelect category to view message from:");
                   System.out.println("1. Sent");
                   System.out.println("2. Stored");
                   System.out.println("3. Discarded");
                   System.out.print("Enter category choice: ");
                   int category = scanner.nextInt();
                   scanner.nextLine();

                   System.out.print("Enter index of message to view: ");
                   int index = scanner.nextInt();
                   scanner.nextLine();

            switch (category) {
                case 1:
                    if (index >= 0 && index < sentMessageCount) {
                        System.out.println(sentMessages[index].printMessages());
            } else {
                System.out.println("Invalid index for sent messages.");
            }
            break;
                case 2:
                    if (index >= 0 && index < storedMessageCount) {
                        System.out.println(storedMessages[index].printMessages());
            } else {
                System.out.println("Invalid index for stored messages.");
            }
            break;
                case 3:
                    if (index >= 0 && index < discardedMessageCount) {
                System.out.println(discardedMessages[index].printMessages());
            } else {
                System.out.println("Invalid index for discarded messages.");
            }
            break;
            
        default:
            System.out.println("Invalid category.");
    }
}
                
        else if (choice == 5) {
                
    // Display Sent Messages
    
            System.out.println("\n--- Sent Messages ---");
            if (sentMessageCount == 0) {
            System.out.println("No sent messages.");
            } else {
                for (int i = 0; i < sentMessageCount; i++) {
                    System.out.println("Message " + (i + 1) + ":");
                    System.out.println(sentMessages[i].printMessages());
                    System.out.println("Hash: " + sentHashes[i]);
                    System.out.println("Message ID: " + sentMessageIDs[i]);
                    System.out.println("---------------------------");
            }
        }

    // Display Stored Messages
    
            System.out.println("\n--- Stored Messages ---");
            if (storedMessageCount == 0) {
                    System.out.println("No stored messages.");
        } else {
            for (int i = 0; i < storedMessageCount; i++) {
                System.out.println("Message " + (i + 1) + ":");
                System.out.println(storedMessages[i].printMessages());
                System.out.println("Hash: " + storedHashes[i]);
                System.out.println("Message ID: " + storedMessageIDs[i]);
                System.out.println("---------------------------");
        }
    }

    // Display Discarded Messages
    
            System.out.println("\n--- Discarded Messages ---");
            if (discardedMessageCount == 0) {
                System.out.println("No discarded messages.");
       } else {
            for (int i = 0; i < discardedMessageCount; i++) {
                System.out.println("Message " + (i + 1) + ":");
                System.out.println(discardedMessages[i].printMessages());
                System.out.println("Hash: " + discardedHashes[i]);
                System.out.println("Message ID: " + discardedMessageIDs[i]);
                System.out.println("---------------------------");
        }
    }
        } else {
            System.out.println("Invalid choice.");
            }
        }
    }

    private static boolean login() {
        int attempts = 3;

        while (attempts > 0) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            for (int i = 0; i < usernames.length; i++) {
                if (usernames[i].equals(username) && passwords[i].equals(password)) {
                    System.out.println("Login successful. Welcome, " + username + "!");
                    return true;
                }
            }

            attempts--;
            System.out.println("Invalid credentials. Attempts remaining: " + attempts);
        }

        return false;
    }
}
