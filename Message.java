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

public class Message {
    
//    Fields
    
    private String messageID;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    private static int totalMessagesSent = 0;
    
//    Constructor
    
    public Message(String recipientCell, String messageText) {
        
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }
    
//    Method 1: Check if message ID is <= 10 charcters
    
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }
    
//    Method 2: Check recipient call is valid (starts with +, <= 10 digits)
    
    public boolean checkRecipientCell() {
        return recipientCell.matches("^\\+\\d{1,12}$");
    }
    
//    Method 3: Create the message hash
    
    public String createMessageHash() {
        String idPart = messageID.substring(0,2);
        String msgCount = String.valueOf(totalMessagesSent + 1);
        String[] words = messageText.split(" ");
        String first = words.length > 0 ? words[0].toUpperCase() : "";
        String last = words.length > 1 ? words[words.length - 1].toUpperCase() : first;
        return idPart + ":" + msgCount + ":" + first + last;
    }
    
//    Method 4: Send, store or disregard the message
    
    public String SentMessage(int choice) {
        if (choice == 1) {
            totalMessagesSent++;
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete message.";
        } else {
            storeMessage();
            return "Message successfully stored";
        }
    }
    
//    Method 5: Print all message details
    
    public String printMessages() {
        return "Message ID: " + messageID + "\n" +
                "Message Hash: " + messageHash + "\n" +
                "Recipient: " + recipientCell + "\n" +
                "Message: " + messageText;
    }
    
//    Method 6: Return total messages sent
    
    public int returnTotalMessages() {
        return totalMessagesSent;
    }
    
//    Method 7: Store message to JSON file
    
    public void storeMessage() {
        try {
            File file = new File("messages.json");
            JSONArray messages;
            
            if (file.exists()) {
                messages = new JSONArray(Files.readString(file.toPath()));
            } else {
                messages = new JSONArray();
            }
            
            JSONObject messageObj = new JSONObject();
            messageObj.put("messageID", messageID);
            messageObj.put("recipientCell", recipientCell);
            messageObj.put("messageText", messageText);
            messageObj.put("messageHash", messageHash);
            
            messages.put(messageObj);
            
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(messages.toString(4)); // Pretty print
            }
            
            System.out.println("Message stored in messages.json.");
        } catch (IOException e) {
            System.out.println("Error saving message: " + e.getMessage());
        }
    }
    
//    Helper: Generate random 10 digit message ID
    
    public String generateMessageID() {
        Random random = new Random();
        long number = (long) (random.nextDouble() * 10000000000L);
        return String.format("%010d", number);
    }   
}
