// Employee.java
import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private String employeeID;
    private String name;
    private String designation;
    private double salary;

    public Employee(String employeeID, String name, String designation, double salary) {
        this.employeeID = employeeID;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public String getEmployeeID() { return employeeID; }
    public String getName() { return name; }
    public String getDesignation() { return designation; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Designation: %s | Salary: %.2f",
                employeeID, name, designation, salary);
    }
}
