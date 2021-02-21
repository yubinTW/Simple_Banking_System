import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        System.out.printf("%d ", x);
        while (x != 1) {
            if (x % 2 == 0) {
                x /= 2;
            } else {
                x *= 3;
                x += 1;
            }
            System.out.printf("%d ", x);
        }
    }
}