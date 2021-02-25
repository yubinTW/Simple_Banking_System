package banking;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String filename = args[1];

        Bank bank = new Bank(filename);
        Scanner scanner = new Scanner(System.in);
        String x;
        while (true) {
            System.out.println("1. Create an account\n" +
                    "2. Log into account\n" +
                    "0. Exit");
            x = scanner.next();
            if (x.equals("1")) {
                bank.createAccount();
            }
            if (x.equals("2")) {
                System.out.println("Enter your card number:");
                String cardNumber = scanner.next();
                System.out.println("Enter your PIN:");
                String pin = scanner.next();
                if (bank.loginAccount(cardNumber, pin)) {
                    System.out.println("You have successfully logged in!");
                    while (true) {
                        System.out.println("1. Balance\n" +
                                "2. Log out\n" +
                                "0. Exit");
                        x = scanner.next();
                        if (x.equals("1")) {
                            System.out.println("Balance: 0");
                        }
                        if (x.equals("2")) {
                            System.out.println("You have successfully logged out!");
                            break;
                        }
                        if (x.equals("0")) {
                            break;
                        }
                    }
                } else {
                    System.out.println("Wrong card number or PIN!");
                }
            }
            if (x.equals("0")) {
                System.out.println("Bye!");
                break;
            }
        }

    }

}