/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Message;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Message {
    private String messageID;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    private String status; // "Sent", "Disregarded", "Stored"

    // Dynamic Lists (Arrays) as required in Part 3
    public static ArrayList<Message> sentMessages = new ArrayList<>();
    public static ArrayList<Message> disregardedMessages = new ArrayList<>();
    public static ArrayList<Message> storedMessages = new ArrayList<>();

    public Message(String recipientCell, String messageText, int messageNum) {
        this.messageID = generateMessageID();
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageHash = createMessageHash(messageNum);
        this.status = "";
    }

    private String generateMessageID() {
        Random rand = new Random();
        long num = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(num);
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    public boolean checkRecipientCell() {
        return recipientCell != null && recipientCell.matches("^\\+27[0-9]{9}$");
    }

    public String createMessageHash(int messageNum) {
        if (messageText == null || messageText.trim().isEmpty()) return "";
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");
        
        String prefix = messageID.substring(0, Math.min(2, messageID.length()));
        return (prefix + ":" + messageNum + ":" + firstWord + lastWord).toUpperCase();
    }

    public String sentMessage(int option) {
        switch (option) {
            case 1:
                this.status = "Sent";
                sentMessages.add(this);
                return "Message successfully sent.";
            case 2:
                this.status = "Disregarded";
                disregardedMessages.add(this);
                return "Press 0 to delete the message.";
            case 3:
                this.status = "Stored";
                storedMessages.add(this);
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }

    public void storeMessage() {
        // Research/Implementation for JSON storage
        try (FileWriter file = new FileWriter("stored_messages.json", true)) {
            String json = String.format("{\"ID\":\"%s\", \"Recipient\":\"%s\", \"Message\":\"%s\", \"Hash\":\"%s\"}\n",
                    messageID, recipientCell, messageText, messageHash);
            file.write(json);
        } catch (IOException e) {
            System.out.println("Error saving message to JSON: " + e.getMessage());
        }
    }

    public static int returnTotalMessages() {
        return sentMessages.size();
    }

    // Part 3 Features
   
public static void displayStoredMessagesSenderRecipient() {
    System.out.println("\n--- STORED MESSAGES (SENDER & RECIPIENT) ---");

    if (storedMessages.isEmpty()) {
        System.out.println("No stored messages found.");
        return;
    }

    for (Message msg : storedMessages) {
        System.out.println("Message ID: " + msg.getMessageID());
        System.out.println("Recipient: " + msg.getRecipientCell());
        System.out.println("Message: " + msg.getMessageText());
        System.out.println("Message Hash: " + msg.getMessageHash());
        System.out.println("--------------------------------");
    }
}


    public static void displayLongestStoredMessage() {
        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages found.");
            return;
        }
        Message longest = storedMessages.get(0);
        for (Message msg : storedMessages) {
            if (msg.getMessageText().length() > longest.getMessageText().length()) {
                longest = msg;
            }
        }
        System.out.println("Longest Stored Message: " + longest.getMessageText());
    }

    public static void searchByMessageID(String id) {
        for (Message msg : storedMessages) {
            if (msg.getMessageID().equals(id)) {
                System.out.println("Found -> Recipient: " + msg.getRecipientCell() + " | Message: " + msg.getMessageText());
                return;
            }
        }
        System.out.println("Message ID not found.");
    }

    public static void searchByRecipient(String recipient) {
        boolean found = false;
        for (Message msg : storedMessages) {
            if (msg.getRecipientCell().equals(recipient)) {
                System.out.println("Message: " + msg.getMessageText());
                found = true;
            }
        }
        if (!found) System.out.println("No messages found for this recipient.");
    }

    public static void deleteByHash(String hash) {
        boolean removed = storedMessages.removeIf(msg -> msg.getMessageHash().equalsIgnoreCase(hash));
        if (removed) {
            System.out.println("Message with hash " + hash + " successfully deleted.");
        } else {
            System.out.println("Hash not found.");
        }
    }

    public static void printReport() {
        System.out.println("\n--- SENT MESSAGES REPORT ---");
        for (Message msg : sentMessages) {
            System.out.println("ID: " + msg.getMessageID() + " | Hash: " + msg.getMessageHash() + 
                               " | Recipient: " + msg.getRecipientCell() + " | Message: " + msg.getMessageText());
        }
        System.out.println("Total Sent Messages: " + returnTotalMessages());
    }

    // Getters
    public String getMessageID() { return messageID; }
    public String getRecipientCell() { return recipientCell; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }
    public String getStatus() { return status; }
}
