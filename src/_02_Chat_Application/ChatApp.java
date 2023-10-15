package _02_Chat_Application;

import java.util.Scanner;

public class ChatApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to start the (1)Server or (2)Client?");
        int choice = scanner.nextInt();
        if (choice == 1) {
            Server.main(args);
        } else if (choice == 2) {
            Server.main(args);
        } else {
            System.out.println("Invalid choice!");
        }
    }
}