import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String s = scanner.next();
            if (s.equals("0")) {
                break;
            }
            try {
                System.out.println(Integer.parseInt(s) * 10);
            } catch (NumberFormatException e) {
                System.out.println("Invalid user input: " + s);
            }
        }
    }
}