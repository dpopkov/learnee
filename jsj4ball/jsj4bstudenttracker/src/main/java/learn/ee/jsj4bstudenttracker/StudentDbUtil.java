package learn.ee.jsj4bstudenttracker;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDbUtil {
    private final DataSource dataSource;

    public StudentDbUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Student> getStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, first_name, last_name, email FROM student");
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                Student student = new Student(id, firstName, lastName, email);
                students.add(student);
            }
        }
        return students;
    }

    public void add(Student student) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO student (first_name, last_name, email) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getEmail());
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                int id = keys.getInt(1);
                student.setId(id);
            }
        }
    }

    /** Retrieves a student with the specified id or null if nothing found. */
    public Student getById(int studentId) throws SQLException {
        Student student = null;
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT id, first_name, last_name, email FROM student WHERE id = ?");
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                student = new Student(id, firstName, lastName, email);
            }
        }
        return student;
    }

    public boolean update(Student student) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE student SET first_name = ?, last_name = ?, email = ? WHERE id = ?");
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getEmail());
            stmt.setInt(4, student.getId());
            int rowCount = stmt.executeUpdate();
            return rowCount == 1;
        }
    }

    public void deleteById(int studentId) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM student WHERE id = ?");
            stmt.setInt(1, studentId);
            stmt.executeUpdate();
        }
    }
}
