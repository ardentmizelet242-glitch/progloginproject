/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Message.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 1);
        assertTrue(msg.getMessageText().length() <= 250);
    }

    @Test
    public void testRecipientNumberFormat() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 1);
        assertTrue(msg.checkRecipientCell());
    }

    @Test
    public void testMessageHashFormat() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 1);
        String hash = msg.createMessageHash(1);
        assertNotNull(hash);
        assertTrue(hash.contains(":1:HITONIGHT"));
    }

    @Test
    public void testSendMessageOption() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 1);
        String response = msg.sentMessage(1);
        assertEquals("Message successfully sent.", response);
    }
    @Test
public void testRecipientNumberFormatFailure() {
    Message msg = new Message("0838884567", "Hello", 1);
    assertFalse(msg.checkRecipientCell());
}

@Test
public void testDisregardMessageOption() {
    Message msg = new Message("+27718693002", "Hello", 1);
    String response = msg.sentMessage(2);
    assertEquals("Press 0 to delete the message.", response);
    assertEquals("Disregarded", msg.getStatus());
}
}
