import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDatabaseOperations {
    private final String url = "jdbc:postgresql://localhost:5432/University ";
    private final String user = "postgres";
    private final String password = "5033";

    //Establish connection to the PostgreSQL database.
    private Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        if (conn != null) {
            System.out.println("Connected to PostgreSQL successfully!");
        } else {
            System.out.println("Failed to establish connection.");
        }
        return conn;
    }



    //Retrieves and displays all records from the students table.

    public void getAllStudents() {
        String SQL = "SELECT * FROM students";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                System.out.println(rs.getInt("student_id") + "\t"
                        + rs.getString("first_name") + "\t"
                        + rs.getString("last_name") + "\t"
                        + rs.getString("email") + "\t"
                        + rs.getDate("enrollment_date"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

     //Inserts a new student record into the students table.

    public void addStudent(String first_name, String last_name, String email, String enrollment_date) {
        String SQL = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setString(3, email);
            pstmt.setDate(4, java.sql.Date.valueOf(enrollment_date));
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("A student was added successfully!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Updates the email address for a student with the specified student_id.

    public void updateStudentEmail(int student_id, String new_email) {
        String SQL = "UPDATE students SET email = ? WHERE student_id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, new_email);
            pstmt.setInt(2, student_id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Student email updated successfully!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Deletes the record of the student with the specified student_id.

    public void deleteStudent(int student_id) {
        String SQL = "DELETE FROM students WHERE student_id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, student_id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("A student was deleted successfully!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {

        StudentDatabaseOperations dbOperations = new StudentDatabaseOperations();

        // Display all students
        dbOperations.getAllStudents();

        // Add a new student
        /*dbOperations.addStudent("Lewis", "Hamilton", "ferreri@example.com", "2023-10-01");
        dbOperations.getAllStudents();*/

        // Update a student's email
        /*dbOperations.updateStudentEmail(1, "don.toliver@example.com");
        dbOperations.getAllStudents();*/

        // Delete a student
        /*dbOperations.deleteStudent(2);
        dbOperations.getAllStudents();*/
    }


}
