package learn.ee.jsj4bstudenttracker;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/students")
public class StudentControllerServlet extends HttpServlet {
    private StudentDbUtil studentDbUtil;

    @Override
    public void init() throws ServletException {
        try {
            Context ctx = new InitialContext();
            DataSource dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/jsj2b_student_tracker");
            studentDbUtil = new StudentDbUtil(dataSource);
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        if (command == null) {
            command = "LIST";
        }
        switch (command) {
            case "ADD":
                addStudent(req, resp);
                break;
            case "LOAD":
                loadStudent(req, resp);
                break;
            case "UPDATE":
                updateStudent(req, resp);
                break;
            case "LIST":
            default:
                listStudents(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void addStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        Student student = new Student(firstName, lastName, email);
        try {
            studentDbUtil.add(student);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        listStudents(req, resp);
    }

    private void loadStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("studentId");
        int id = Integer.parseInt(idStr);
        Student student;
        try {
            student = studentDbUtil.getById(id);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        req.setAttribute("STUDENT", student);
        req.getRequestDispatcher("/update-student-form.jsp").forward(req, resp);
    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("studentId"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        Student student = new Student(id, firstName, lastName, email);
        try {
            studentDbUtil.update(student);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        listStudents(req, resp);
    }

    private void listStudents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students;
        try {
            students = studentDbUtil.getStudents();
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        req.setAttribute("STUDENTS_LIST", students);
        req.getRequestDispatcher("/list-students.jsp").forward(req, resp);
    }
}
