// SumAutoboxing.java
import java.util.ArrayList;
import java.util.Scanner;

public class SumAutoboxing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter integers separated by spaces (e.g. 10 20 30):");
        String line = sc.nextLine().trim();

        if (line.isEmpty()) {
            System.out.println("No numbers provided. Sum = 0");
            sc.close();
            return;
        }

        String[] tokens = line.split("\\s+");
        ArrayList<Integer> numbers = new ArrayList<>();

        for (String t : tokens) {
            try {
                // parse string -> primitive int, then autobox to Integer when adding
                int val = Integer.parseInt(t);
                numbers.add(val); // autoboxing from int -> Integer
            } catch (NumberFormatException nfe) {
                System.out.printf("Skipping invalid integer: '%s'%n", t);
            }
        }

        // unboxing happens automatically when using int primitive
        int sum = 0;
        for (Integer num : numbers) {
            sum += num; // unboxing Integer -> int
        }

        System.out.println("Numbers stored: " + numbers);
        System.out.println("Total sum = " + sum);
        sc.close();
    }
}
