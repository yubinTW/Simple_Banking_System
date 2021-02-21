import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        if (x < 1) {
            System.out.println("no army");
        } else if (x >= 1 && x <= 19) {
            System.out.println("pack");
        } else if (x >= 20 && x <= 249) {
            System.out.println("throng");
        } else if (x >= 250 && x <= 999) {
            System.out.println("zounds");
        } else {
            System.out.println("legion");
        }
    }
}