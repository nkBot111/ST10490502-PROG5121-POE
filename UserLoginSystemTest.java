/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.userloginsystem;

/**
 *
 * @author RC_Student_lab
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserLoginSystemTest {

private Message[] sentMessages = new Message[5];
private Message[] storedMessages = new Message[5];
private Message[] disregardedMessages = new Message[5];
private int sentIndex = 0, storedIndex = 0, discardIndex = 0;

@BeforeEach
public void setUp() {
addTestMessage("+27834557896", "Did you get the cake?", "Sent");
addTestMessage("+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
addTestMessage("+27834484567", "Yohoooo, I am at your gate.", "Disregard");
addTestMessage("08388884567", "It is dinner time !", "Sent");
addTestMessage("+27838884567", "Ok, I am leaving without you.", "Stored");
}

private void addTestMessage(String recipient, String text, String flag) {
Message msg = new Message(recipient, text);
if (flag.equalsIgnoreCase("Sent")) {
sentMessages[sentIndex++] = msg;
} else if (flag.equalsIgnoreCase("Stored")) {
storedMessages[storedIndex++] = msg;
} else if (flag.equalsIgnoreCase("Disregard")) {
disregardedMessages[discardIndex++] = msg;
}
}

//  Success Tests

@Test
public void testSentMessagesArrayCorrectlyPopulated() {
assertEquals("Did you get the cake?", sentMessages[0].getMessageText());
assertEquals("It is dinner time !", sentMessages[1].getMessageText());
}

@Test
public void testDisplayLongestMessage() {
String longest = "";
for (Message msg : storedMessages) {
if (msg != null && msg.getMessageText().length() > longest.length()) {
longest = msg.getMessageText();
}
}
assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
}

@Test
public void testSearchByMessageID() {
String foundRecipient = "";
for (Message msg : sentMessages) {
if (msg != null && msg.getRecipientCell().equals("08388884567")) {
foundRecipient = msg.getRecipientCell();
}
}
assertEquals("08388884567", foundRecipient);
}

@Test
public void testSearchMessagesByRecipient() {
StringBuilder messages = new StringBuilder();
for (Message msg : storedMessages) {
if (msg != null && msg.getRecipientCell().equals("+27838884567")) {
messages.append(msg.getMessageText()).append(" ");
}
}
String result = messages.toString();
assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
assertTrue(result.contains("Ok, I am leaving without you."));
}

@Test
public void testDeleteMessageUsingHash() {
boolean deleted = false;
for (int i = 0; i < storedMessages.length; i++) {
if (storedMessages[i] != null && storedMessages[i].getMessageText().contains("Where are you?")) {
storedMessages[i] = null;
deleted = true;
break;
}
}
assertTrue(deleted);
}

@Test
public void testDisplayReport() {
for (Message msg : sentMessages) {
if (msg != null) {
String report = msg.createMessageHash() + " | " + msg.getRecipientCell() + " | " + msg.getMessageText();
assertTrue(report.contains(msg.getMessageText()));
assertTrue(report.contains(msg.getRecipientCell()));
}
}
}

//  Failure Tests

@Test
public void testSearchNonExistentRecipient() {
boolean found = false;
for (Message msg : storedMessages) {
if (msg != null && msg.getRecipientCell().equals("+27999999999")) {
found = true;
break;
}
}
assertFalse(found);
}

@Test
public void testDeleteNonExistentMessage() {
boolean deleted = false;
for (int i = 0; i < storedMessages.length; i++) {
if (storedMessages[i] != null && storedMessages[i].getMessageText().contains("This text doesn't exist")) {
storedMessages[i] = null;
deleted = true;
break;
}
}
assertFalse(deleted);
}
}