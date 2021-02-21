package banking;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String cardNumber = "";
        String pin = "";

        while (true) {
            System.out.println("1. Create an account\n" +
                    "2. Log into account\n" +
                    "0. Exit");
            String input = scanner.next();
            if (input.equals("0")) {
                System.out.println("Bye!");
                break;
            }
            if (input.equals("1")) {
                Random random = new Random();
                cardNumber = String.format("400000%05d%05d", random.nextInt(100000), random.nextInt(100000));
                pin = String.format("%04d", random.nextInt(10000));

                System.out.println("Your card number:\n" +
                        cardNumber +
                        "\n" +
                        "Your card PIN:\n" +
                        pin);
            }
            if (input.equals("2")) {
                System.out.println("Enter your card number:");
                String s1 = scanner.next();
                System.out.println("Enter your PIN:");
                String s2 = scanner.next();

                if (cardNumber.equals(s1) && pin.equals(s2)) {
                    System.out.println("You have successfully logged in!");
                    System.out.println("\n" +
                            "1. Balance\n" +
                            "2. Log out\n" +
                            "0. Exit");
                    String x = scanner.next();
                    if (x.equals("1")) {
                        System.out.println("Balance: 0");
                    }
                    if (x.equals("2")) {
                        System.out.println("You have successfully logged out!");
                    }
                } else {
                    System.out.println("Wrong card number or PIN!");
                }
            }

        }

    }
}