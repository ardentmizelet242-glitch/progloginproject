/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @Test
    public void testCheckUserNameSuccess() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "nt");
        assertTrue(login.checkUserName());
    }

    @Test
    public void testCheckUserNameFailure() {
        Login login = new Login("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976", "Kyle", "nt");
        assertFalse(login.checkUserName());
    }

    @Test
    public void testCheckPasswordSuccess() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "nt");
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testCheckPasswordFailure() {
        Login login = new Login("kyl_1", "password", "+27838968976", "Kyle", "nt");
        assertFalse(login.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneSuccess() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "nt");
        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneFailure() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "08966553", "Kyle", "nt");
        assertFalse(login.checkCellPhoneNumber());
    }
}

