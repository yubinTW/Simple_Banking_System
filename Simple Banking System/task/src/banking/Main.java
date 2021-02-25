package banking;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String filename = args[1];
//        String filename = "card.s3db";

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
                                "2. Add income\n" +
                                "3. Do transfer\n" +
                                "4. Close account\n" +
                                "5. Log out\n" +
                                "0. Exit");
                        x = scanner.next();
                        if (x.equals("1")) {
                            int balance = bank.getBalance(cardNumber, pin);
                            System.out.println("Balance: " + balance);
                        }
                        if (x.equals("2")) {
                            System.out.println("Enter income:");
                            x = scanner.next();
                            bank.addIncome(cardNumber, Integer.parseInt(x));
                            System.out.println("Income was added!");
                        }
                        if (x.equals("3")) {
                            System.out.println("Transfer");
                            System.out.println("Enter card number:");
                            String toNumber = scanner.next();
                            if (bank.isValidCard(toNumber)) {
                                if (bank.isExistCard(toNumber)) {
                                    System.out.println("Enter how much money you want to transfer:");
                                    int transferBalance = scanner.nextInt();
                                    int currentBalance = bank.getBalance(cardNumber, pin);
                                    if (transferBalance > currentBalance) {
                                        System.out.println("Not enough money!\n");
                                    } else {
                                        bank.transferBalance(cardNumber, toNumber, transferBalance);
                                        System.out.println("Success!");
                                    }
                                } else {
                                    System.out.println("Such a card does not exist.");
                                }
                            } else {
                                System.out.println("Probably you made a mistake in the card number. Please try again!");
                            }
                        }
                        if (x.equals("4")) {
                            bank.deleteAccount(cardNumber);
                            System.out.println("The account has been closed!");
                        }
                        if (x.equals("5")) {
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
        bank.closeBank();
    }

}