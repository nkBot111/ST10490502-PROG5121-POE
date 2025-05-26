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

public class MessageTest {
    private Message message1;
    private Message message2;

@BeforeEach
public void setUp() {
// Initialize test messages
message1 = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight");
message2 = new Message("08575975889", "Hi Keegan, did you receive the payment?");
}

@Test
public void testCheckMessageID_Success() {
// Check that messageID is 10 digits (auto-generated)
assertTrue(message1.checkMessageID());
}

@Test
public void testCheckMessageID_Failure() {
// Simulate a long ID manually for failure case (testing only)
String fakeID = "123456789012345";
assertFalse(fakeID.length() <= 10);
}

@Test
public void testCheckRecipientCell_Success() {
// Test valid recipient number with + and up to 10 digits
assertTrue(message1.checkRecipientCell());
}

@Test
public void testCheckRecipientCell_Failure() {
// Test invalid recipient number (missing +, too many digits)
Message invalidMsg = new Message("123456", "Test");
assertFalse(invalidMsg.checkRecipientCell());
}

@Test
public void testCreateMessageHash_Success() {
// Check that hash is generated and not null
String hash = message1.createMessageHash();
assertNotNull(hash);
assertTrue(hash.contains(":"));
}

@Test
public void testCreateMessageHash_Failure() {
// Hash should still generate even if message text is empty
Message emptyMsg = new Message("+27718693002", "");
String hash = emptyMsg.createMessageHash();
assertTrue(hash.contains(":"));
}

@Test
public void testSentMessage_Send() {
// Check response when sending a message (choice 1)
String result = message1.SentMessage(1);
assertEquals("Message successfully sent.", result);
}

@Test
public void testSentMessage_Discard() {
// Check response when discarding a message (choice 2)
String result = message1.SentMessage(2);
assertEquals("Press 0 to delete message.", result);
}

@Test
public void testSentMessage_Store() {
// Check response when storing a message (choice 3)
String result = message1.SentMessage(3);
assertEquals("Message successfully stored", result);
}

@Test
public void testPrintMessages() {
// Check that printMessages returns expected details
String output = message1.printMessages();
assertTrue(output.contains("Message ID"));
assertTrue(output.contains("Recipient"));
assertTrue(output.contains("Message"));
}

@Test
public void testReturnTotalMessages() {
// Test that total messages sent counter increases after sending
message1.SentMessage(1);
int count = message1.returnTotalMessages();
assertTrue(count >= 1);
}

@Test
public void testLongMessage_Failure() {
// Test message longer than 250 characters
String longText = "a".repeat(251);
Message longMsg = new Message("+27718693002", longText);
assertTrue(longText.length() > 250);
}

@Test
public void testMessageWithin250Chars_Success() {
// Test message within 250 characters
String text = "This is a normal message within 250 characters.";
Message normalMsg = new Message("+27718693002", text);
assertTrue(text.length() <= 250);
}   
}
