/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.userloginsystem;

/**
 *
 * @author RC_Student_lab
 */

import javax.swing.JOptionPane;
import java.util.ArrayList;
import com.mycompany.userloginsystem.Message;

public class UserLoginSystem {
    
    public static void main(String[] args) {
        
//        Show welcome message

        JOptionPane.showMessageDialog(null, "Welcome to QuickChat");
        
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Parks");
        boolean isLoggedIn = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        
        if (!isLoggedIn) {
            JOptionPane.showMessageDialog(null, "Login failed. Exiting...");
            System.exit(0);
        }
        
//        List to store sent messages

        ArrayList<Message> sentMessages = new ArrayList<>();
        
//        Main menu loop

        while (true) {
            String choice = JOptionPane.showInputDialog("Choose an option:\n1. Send Messages\n2. Show Recently Sent Messages\n3. Quit");
            
            if (choice == null || choice.equals("3")) {
                break;
            }
            
            switch (choice) {
                
//        Send messages flow        
                
                case "1":
                    
                    String numStr = JOptionPane.showInputDialog("How many messages would you like to send?");
                    int numMessages = Integer.parseInt(numStr);
                    
                    for (int i = 0; i < numMessages; i++) {
                        String recipient = JOptionPane.showInputDialog("Enter recipient number (start with + and <= 10 digits):");
                        
                        String text = JOptionPane.showInputDialog("Enter message text (max 250 chars):");
                        
                        if (text.length() > 250) {
                            JOptionPane.showMessageDialog(null, "Please enter a message of less than 50 characters.");
                            continue;
                        } else {
                            JOptionPane.showMessageDialog(null, "Message ready to send.");
                        }
                        
                        Message msg = new Message(recipient, text);
                        
//        Confirm message action                
                        
                        String msgChoice = JOptionPane.showInputDialog("Choose an option:\n1. Send Message\n2. Disregard Message\n3. Store Message");
                        int msgOption = Integer.parseInt(msgChoice);
                        
                        String result = msg.SentMessage(msgOption);
                        JOptionPane.showMessageDialog(null, result);
                        
                        if (msgOption == 1) {
                            sentMessages.add(msg);
                        }
                        
//        Disregard does nothing; store can call msg.storeMessage() later
                    }
                    
                    break;
                    
                case "2":
                    JOptionPane.showMessageDialog(null, "Coming Soon.");
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
            }
        }
        
//        After quitting, show all sent messages  
        
        StringBuilder summary = new StringBuilder("Message sent:\n");
        for (Message m : sentMessages) {
            summary.append(m.printMessages()).append("\n\n");
        }
        
        summary.append("Total messages sent: ").append(sentMessages.size());
        JOptionPane.showMessageDialog(null, summary.toString());
    }
}
