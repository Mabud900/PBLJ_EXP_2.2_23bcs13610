// EmployeeManager.java
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManager {
    private static final String FILE_NAME = "employees.dat";

    @SuppressWarnings("unchecked")
    private static ArrayList<Employee> readEmployees() {
        File f = new File(FILE_NAME);
        if (!f.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?>) {
                return (ArrayList<Employee>) obj;
            } else {
                System.err.println("Data format mismatch. Starting with empty list.");
                return new ArrayList<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to read employees: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void writeEmployees(ArrayList<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.err.println("Failed to save employees: " + e.getMessage());
        }
    }

    private static void addEmployee(Scanner sc) {
        System.out.println("Enter employee ID:");
        String id = sc.nextLine().trim();

        System.out.println("Enter name:");
        String name = sc.nextLine().trim();

        System.out.println("Enter designation:");
        String designation = sc.nextLine().trim();

        System.out.println("Enter salary:");
        double salary = 0.0;
        try {
            salary = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary; using 0.0");
        }

        ArrayList<Employee> list = readEmployees();
        Employee emp = new Employee(id, name, designation, salary);
        list.add(emp);
        writeEmployees(list);
        System.out.println("Employee added and saved.");
    }

    private static void displayAll() {
        ArrayList<Employee> list = readEmployees();
        if (list.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("Employees:");
        for (Employee e : list) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Employee Management ---");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1-3): ");

            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    addEmployee(sc);
                    break;
                case "2":
                    displayAll();
                    break;
                case "3":
                    System.out.println("Exiting. Goodbye.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
