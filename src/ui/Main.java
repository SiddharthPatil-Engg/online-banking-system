package ui;

import model.User;
import service.BankingService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        BankingService service = new BankingService();

        System.out.println("===== Online Banking System =====");
        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = service.login(username, password);

        if (user == null) {
            System.out.println("Invalid Login!");
            return;
        }

        while (true) {
            System.out.println("\n1. Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transactions");
            System.out.println("5. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Balance: " + user.getBalance());
                    break;
                case 2:
                    System.out.print("Amount: ");
                    service.deposit(user, sc.nextDouble());
                    break;
                case 3:
                    System.out.print("Amount: ");
                    service.withdraw(user, sc.nextDouble());
                    break;
                case 4:
                    service.showTransactions(user);
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }
}
