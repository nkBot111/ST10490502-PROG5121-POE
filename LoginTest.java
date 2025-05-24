/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.userloginsystem;

/**
 *
 * @author RC_Student_lab
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

@Test
public void testUsernameCorrectlyFormatted() {
Login login = new Login("kyl_1", "Passw0rd!", "+27838968976", "Kyle", "Parks");
assertTrue(login.checkUserName());
}

@Test
public void testUsernameIncorrectlyFormatted() {
Login login = new Login("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Parks");
assertFalse(login.checkUserName());
}

@Test
public void testPasswordMeetsComplexityRequirements() {
Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Parks");
assertTrue(login.checkPasswordComplexity());
}

@Test
public void testPasswordFailsComplexityRequirements() {
Login login = new Login("kyl_1", "password", "+27838968976", "Kyle", "Parks");
assertFalse(login.checkPasswordComplexity());
}

@Test
public void testCellphoneCorrectlyFormatted() {
Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Parks");
assertTrue(login.checkCellphoneNumber());
}

@Test
public void testCellphoneIncorrectlyFormatted() {
Login login = new Login("kyl_1", "Ch&&sec@ke99!", "08966553", "Kyle", "Parks");
assertFalse(login.checkCellphoneNumber());
}

@Test
public void testLoginSuccessMessage() {
Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Parks");
String status = login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!");
assertEquals("Welcome Kyle Parks, it is great to see you again.", status);
}
}
