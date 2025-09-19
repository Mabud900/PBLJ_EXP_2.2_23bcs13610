// Student.java
import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String studentID;
    private String name;
    private double grade;

    public Student(String studentID, String name, double grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }

    public String getStudentID() { return studentID; }
    public String getName() { return name; }
    public double getGrade() { return grade; }

    @Override
    public String toString() {
        return "Student{ID='" + studentID + "', name='" + name + "', grade=" + grade + "}";
    }
}
