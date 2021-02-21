import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int n = scanner.nextInt();
        double m = scanner.nextDouble();

        Random random = new Random(k);
        for (int i=0;i<n;i++){
            double g = random.nextGaussian();
            if (g>m) {
                i = -1;
                k++;
                random = new Random(k);
                continue;
            }
        }
        System.out.println(k);
    }
}