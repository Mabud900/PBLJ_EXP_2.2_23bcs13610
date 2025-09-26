import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {

            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    runInSubprocessOrFallback("SumAutoboxing");
                    break;
                case "2":
                    runInSubprocessOrFallback("StudentSerializationDemo");
                    break;
                case "3":
                    runInSubprocessOrFallback("EmployeeManager");
                    break;
                case "4":
                    Student s = new Student("101", "Alice", 95.5);
                    System.out.println("Demo Student: " + s);
                    break;
                case "5":
                    System.out.println("Exiting launcher.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }
    private static void runInSubprocessOrFallback(String className) {
        try {
            ProcessBuilder pb = new ProcessBuilder("java", className);
            // Inherit IO so the child process shares the console (interactive)
            pb.inheritIO();
            Process p = pb.start();
            int rc = p.waitFor();
            System.out.println("\n[" + className + "] exited with code: " + rc);
        } catch (IOException ioe) {
            System.err.println("Could not start separate JVM for " + className + " (IO): " + ioe.getMessage());
            System.err.println("Falling back to direct call of " + className + ".main()");
            fallbackCall(className);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            System.err.println("Interrupted while waiting for " + className);
        } catch (Exception e) {
            System.err.println("Unexpected error launching " + className + ": " + e.getMessage());
            fallbackCall(className);
        }
    }

       private static void fallbackCall(String className) {
        try {
            switch (className) {
                case "SumAutoboxing":
                    SumAutoboxing.main(new String[]{});
                    break;
                case "StudentSerializationDemo":
                    StudentSerializationDemo.main(new String[]{});
                    break;
                case "EmployeeManager":
                    EmployeeManager.main(new String[]{});
                    break;
                default:
                    System.err.println("Unknown class: " + className);
            }
        } catch (NoClassDefFoundError ncdfe) {
            System.err.println("Class not found for fallback call: " + ncdfe.getMessage());
            System.err.println("Did you compile all .java files? Run: javac *.java");
        } catch (Exception e) {
            System.err.println("Error while executing fallback for " + className + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
