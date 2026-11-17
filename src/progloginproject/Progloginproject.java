/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license.1
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit 1t
his template




 */
package progloginproject;

import java.util.Scanner;
import login.Login;
import Message.Message;

public class Progloginproject {  

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- REGISTRATION ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter cell phone number (+27...): ");
        String phone = scanner.nextLine();
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        Login user = new Login(username, password, phone, firstName, lastName);
        String regStatus = user.registerUser();
        System.out.println(regStatus);

        if (!regStatus.equals("User registered successfully.")) {
            System.out.println("Program stopped due to registration failure.");
            return;
        }

        System.out.println("\n--- LOGIN ---");
        System.out.print("Enter username: ");
        String inputUser = scanner.nextLine();
        System.out.print("Enter password: ");
        String inputPass = scanner.nextLine();

        boolean loggedIn = user.loginUser(inputUser, inputPass);
        System.out.println(user.returnLoginStatus(loggedIn));

        if (loggedIn) {
            System.out.println("\nWelcome to QuickChat.");
            boolean running = true;
            
            while (running) {
                System.out.println("\nMenu:");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.println("4) Stored Messages");
                System.out.print("Choice: ");
                
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (option) {
                    case 1 -> {
                        System.out.print("How many messages would you like to send? ");
                        int count = scanner.nextInt();
                        scanner.nextLine();

                        for (int i = 0; i < count; i++) {
                            System.out.print("Enter recipient cell number: ");
                            String recipient = scanner.nextLine();
                            System.out.print("Enter message: ");
                            String msgText = scanner.nextLine();

                            if (msgText.length() > 250) {
                                System.out.println("Please enter a message of less than 250 characters.");
                                continue;
                            }

                           
Message msg = new Message(recipient, msgText, i + 1);

System.out.println("\nChoose what to do with this message:");
System.out.println("1) Send Message");
System.out.println("2) Disregard Message");
System.out.println("3) Store Message");

System.out.print("Your choice: ");
int messageOption = scanner.nextInt();
scanner.nextLine();

System.out.println(msg.sentMessage(messageOption));

System.out.println("\nMessage Details:");
System.out.println("Message ID: " + msg.getMessageID());
System.out.println("Message Hash: " + msg.getMessageHash());
System.out.println("Recipient: " + msg.getRecipientCell());
System.out.println("Message: " + msg.getMessageText());

                        }
                    }
                   case 2 -> System.out.println("Coming Soon.");
case 3 -> running = false;

case 4 -> {
    boolean storedMenu = true;

    while (storedMenu) {
        System.out.println("\n--- STORED MESSAGES MENU ---");
        System.out.println("1) Display stored messages");
        System.out.println("2) Display longest stored message");
        System.out.println("3) Search by Message ID");
        System.out.println("4) Search by recipient");
        System.out.println("5) Delete message by Hash");
        System.out.println("6) Display full report");
        System.out.println("7) Back to main menu");
        System.out.print("Choice: ");

        int storedOption = scanner.nextInt();
        scanner.nextLine();

        switch (storedOption) {
            case 1 -> Message.displayStoredMessagesSenderRecipient();
            case 2 -> Message.displayLongestStoredMessage();
            case 3 -> {
                System.out.print("Enter Message ID: ");
                String id = scanner.nextLine();
                Message.searchByMessageID(id);
            }
            case 4 -> {
                System.out.print("Enter recipient number: ");
                String recipient = scanner.nextLine();
                Message.searchByRecipient(recipient);
            }
            case 5 -> {
                System.out.print("Enter Message Hash: ");
                String hash = scanner.nextLine();
                Message.deleteByHash(hash);
            }
            case 6 -> Message.printReport();
            case 7 -> storedMenu = false;
            default -> System.out.println("Invalid option.");
        }
    }
}

default -> System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}