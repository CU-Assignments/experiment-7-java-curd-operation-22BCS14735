import java.sql.*;

public class FetchEmployeeData {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Company";  // Database URL
        String user = "root";  // MySQL username
        String password = "";  // MySQL password

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish Connection
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to Database!");

            // Execute SQL Query
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");

            // Process Result Set
            while (rs.next()) {
                System.out.println("EmpID: " + rs.getInt("EmpID") + ", Name: " + rs.getString("Name") + ", Salary: " + rs.getDouble("Salary"));
            }

            // Close connections
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
