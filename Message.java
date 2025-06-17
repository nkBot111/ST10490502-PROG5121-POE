package com.mycompany.userloginsystem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RC_Student_lab
 */


import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

public class Message {

    // Fields
    private String messageID;
    private String recipientCell;
    private String messageText;
    private String messageHash;

    // Tracks the number of successfully sent messages
    private static int totalMessagesSent = 0;

    // NEW: Stores all message texts for message history tracking
    private static ArrayList<String> messageHistory = new ArrayList<>();

    // Constructor
    public Message(String recipientCell, String messageText) {
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
        messageHistory.add(messageText);  // Add message to history upon creation
    }

    // Method 1: Check if message ID is <= 10 characters
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // Method 2: Check if recipient number starts with + and contains <= 12 digits
    public boolean checkRecipientCell() {
        return recipientCell.matches("^\\+\\d{1,12}$");
    }

    // Method 3: Creates a unique hash using ID substring, message count, and content keywords
    public String createMessageHash() {
        String idPart = messageID.substring(0, 2);
        String msgCount = String.valueOf(totalMessagesSent + 1);
        String[] words = messageText.split(" ");
        String first = words.length > 0 ? words[0].toUpperCase() : "";
        String last = words.length > 1 ? words[words.length - 1].toUpperCase() : first;
        return idPart + ":" + msgCount + ":" + first + last;
    }

    // Method 4: Handles message sending based on user choice
    public String SentMessage(int choice) {
        if (choice == 1) {
            totalMessagesSent++;
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete message.";
        } else {
            storeMessage();  // Save to JSON
            return "Message successfully stored";
        }
    }

    // Method 5: Print all message details in a readable format
    public String printMessages() {
        return "Message ID: " + messageID + "\n"
             + "Message Hash: " + messageHash + "\n"
             + "Recipient: " + recipientCell + "\n"
             + "Message: " + messageText;
    }

    // Method 6: Return total number of successfully sent messages
    public int returnTotalMessages() {
        return totalMessagesSent;
    }

    // Method 7: Store this message as a JSON object inside a JSON array file (messages.json)
    public void storeMessage() {
        try {
            File file = new File("messages.json");
            JSONArray messages;

    // Load existing messages if file exists
            if (file.exists()) {
                messages = new JSONArray(Files.readString(file.toPath()));
            } else {
                messages = new JSONArray();
            }

    // Create new message object
            JSONObject messageObj = new JSONObject();
            messageObj.put("messageID", messageID);
            messageObj.put("recipientCell", recipientCell);
            messageObj.put("messageText", messageText);
            messageObj.put("messageHash", messageHash);

            // Append new message to array
            messages.put(messageObj);

    // Write updated array back to file
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(messages.toString(4));  // Pretty print with indentation
            }

            System.out.println("Message stored in messages.json.");
        } catch (IOException e) {
            System.out.println("Error saving message: " + e.getMessage());
        }
    }

    // Getter for message history
    public static ArrayList<String> getMessageHistory() {
        return messageHistory;
    }

    // Helper: Generate random 10-digit numeric message ID
    public String generateMessageID() {
        Random random = new Random();
        long number = (long) (random.nextDouble() * 10000000000L);
        return String.format("%010d", number);
    }
    
    // Getter for message text
    public String getMessageText() {
        return messageText;
    }

    // Getter for recipient cell
    public String getRecipientCell() {
        return recipientCell;
    }

    // Getter for message ID
    public String getMessageID() {
        return messageID;
    }

    // Getter for message hash
    public String getMessageHash() {
        return messageHash;
    }
}

