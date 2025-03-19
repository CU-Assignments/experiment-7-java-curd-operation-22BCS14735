import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
    static final String URL = "jdbc:mysql://localhost:3306/Shop";
    static final String USER = "root";
    static final String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner sc = new Scanner(System.in)) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            while (true) {
                System.out.println("\n1. Add Product\n2. View Products\n3. Update Product\n4. Delete Product\n5. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                
                switch (choice) {
                    case 1:
                        addProduct(con, sc);
                        break;
                    case 2:
                        viewProducts(con);
                        break;
                    case 3:
                        updateProduct(con, sc);
                        break;
                    case 4:
                        deleteProduct(con, sc);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add Product
    static void addProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter product name: ");
        String name = sc.next();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();

        String query = "INSERT INTO Product (ProductName, Price, Quantity) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, quantity);
            ps.executeUpdate();
            System.out.println("Product added successfully!");
        }
    }

    // View Products
    static void viewProducts(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Product");
        while (rs.next()) {
            System.out.println(rs.getInt("ProductID") + " | " + rs.getString("ProductName") + " | " + rs.getDouble("Price") + " | " + rs.getInt("Quantity"));
        }
    }

    // Update Product
    static void updateProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Product ID to update: ");
        int id = sc.nextInt();
        System.out.print("Enter new price: ");
        double price = sc.nextDouble();
        System.out.print("Enter new quantity: ");
        int quantity = sc.nextInt();

        String query = "UPDATE Product SET Price = ?, Quantity = ? WHERE ProductID = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setDouble(1, price);
            ps.setInt(2, quantity);
            ps.setInt(3, id);
            ps.executeUpdate();
            System.out.println("Product updated successfully!");
        }
    }

    // Delete Product
    static void deleteProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Product ID to delete: ");
        int id = sc.nextInt();

        String query = "DELETE FROM Product WHERE ProductID = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Product deleted successfully!");
        }
    }
}
