// StudentSerializationDemo.java
import java.io.*;
import java.util.Scanner;

public class StudentSerializationDemo {
    private static final String FILE_NAME = "student.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter student ID:");
        String id = sc.nextLine().trim();

        System.out.println("Enter name:");
        String name = sc.nextLine().trim();

        System.out.println("Enter grade (e.g. 8.5):");
        double grade = 0.0;
        try {
            grade = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid grade input; using 0.0");
        }

        Student s = new Student(id, name, grade);

        // Serialize
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(s);
            System.out.println("Student serialized to " + FILE_NAME);
        } catch (IOException ioe) {
            System.err.println("Error during serialization: " + ioe.getMessage());
            sc.close();
            return;
        }

        // Deserialize
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Object obj = ois.readObject();
            if (obj instanceof Student) {
                Student deserialized = (Student) obj;
                System.out.println("Deserialized student: " + deserialized);
            } else {
                System.out.println("Unexpected object in file.");
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Error during deserialization: " + ex.getMessage());
        }

        sc.close();
    }
}
