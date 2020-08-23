package learn.ee.jsj4b06servletdeom.mvc2;

import java.util.ArrayList;
import java.util.List;

public class StudentDataUtil {
    public static List<Student> getStudents() {
        List<Student> list = new ArrayList<>();
        list.add(new Student("Alice", "Doe", "alice@example.org"));
        list.add(new Student("Michael", "Low", "michael@example.org"));
        list.add(new Student("Bobby", "Draper", "bobby@example.org"));
        return list;
    }
}
