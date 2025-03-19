import java.sql.*;
import java.util.Scanner;

// Model: Student class
class Student {
    private int studentID;
    private String name;
    private String department;
    private double marks;

    public Student(String name, String department, double marks) {
        this.name = name;
        this.department = department;
        this.marks = marks;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getMarks() {
        return marks;
    }
}

// Controller: StudentDAO class (Handles Database Operations)
class StudentDAO {
    private Connection con;

    public StudentDAO() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/University", "root", ""); 
        } catch (SQLException e) {
            System.out.println("Database Connection Failed: " + e.getMessage());
        }
    }

    public void addStudent(Student student) {
        try {
            String query = "INSERT INTO Student (Name, Department, Marks) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, student.getName());
            ps.setString(2, student.getDepartment());
            ps.setDouble(3, student.getMarks());
            ps.executeUpdate();
            System.out.println("Student Added Successfully!");
        } catch (SQLException e) {
            System.out.println("Error Adding Student: " + e.getMessage());
        }
    }

    public void displayStudents() {
        try {
            String query = "SELECT * FROM Student";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("\n--- Student Records ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("StudentID") +
                        ", Name: " + rs.getString("Name") +
                        ", Department: " + rs.getString("Department") +
                        ", Marks: " + rs.getDouble("Marks"));
            }
        } catch (SQLException e) {
            System.out.println("Error Fetching Students: " + e.getMessage());
        }
    }
}

// View: User Interaction
public class StudentManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Exit");
            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter Student Name: ");
                String name = sc.next();
                System.out.print("Enter Department: ");
                String dept = sc.next();
                System.out.print("Enter Marks: ");
                double marks = sc.nextDouble();

                Student student = new Student(name, dept, marks);
                dao.addStudent(student);
            } else if (choice == 2) {
                dao.displayStudents();
            } else {
                System.out.println("Exiting...");
                break;
            }
        }
        sc.close();
    }
}
