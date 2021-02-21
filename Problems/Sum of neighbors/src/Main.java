import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        ArrayList<int[]> arr = new ArrayList();
        while (true) {
            String s = scanner.nextLine();
            if (s.equals("end")) {
                break;
            }
            String[] ss = s.split(" ");
            int[] intArr = new int[ss.length];
            for (int i = 0; i < ss.length; i++) {
                intArr[i] = Integer.parseInt(ss[i]);
            }
            arr.add(intArr);
        }
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.get(0).length; j++) {
                int sum = 0;
                sum += (i - 1 >= 0) ? arr.get(i - 1)[j] : arr.get(i - 1 + arr.size())[j];
                sum += (i + 1 < arr.size()) ? arr.get(i + 1)[j] : arr.get(i + 1 - arr.size())[j];
                sum += (j - 1 >= 0) ? arr.get(i)[j - 1] : arr.get(i)[j - 1 + arr.get(i).length];
                sum += (j + 1 < arr.get(i).length) ? arr.get(i)[j + 1] : arr.get(i)[j + 1 - arr.get(i).length];
                System.out.printf("%d ", sum);
            }
            System.out.println();
        }
    }
}