/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
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
                            System.out.println("\nMessage Sent Details:");
                            System.out.println("Message ID: " + msg.getMessageID());
                            System.out.println("Message Hash: " + msg.getMessageHash());
                            System.out.println("Recipient: " + msg.getRecipientCell());
                            System.out.println("Message: " + msg.getMessageText());
                        }
                    }
                    case 2 -> System.out.println("Coming Soon.");
                    case 3 -> running = false;
                    default -> System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}