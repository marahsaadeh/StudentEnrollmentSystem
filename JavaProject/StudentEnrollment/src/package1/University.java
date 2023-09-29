package package1;
import java.util.ArrayList;
import java.util.List;

public class University {
//class that acts as the central component for managing students and courses
    private List<Student> students;
    private List<Course> courses;
 
    public University() {
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
    }
//Getter function
    public List<Student> getStudents() {
        return students;
    }
     
    public List<Course> getCourses() {
        return courses;
    }

//When the user enters the student's ID number, whether to remove them from a course,add them,
//or simply to view the courses they have enrolled in...
    public Student findStudentByID(int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                return student; // find student
            }
        }
        return null; // He was not found, so it returned null.
    }
    //The same thing here.
    public Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course; 
            }
        }
        return null; 
    }
        
/*Adding a new course or a new student. The University class have 
ArrayLists that contain all the students and ArrayLists containing all the courses,
 so the addition new will be done on these lists. */
public void addStudent(Student student) {
    students.add(student);
}

public void addCourse(Course course) {
    courses.add(course);
}


/*I placed this code in a function because it's repeated multiple times in the main . 
So, the function is called instead of repeating the code. */
public void allCoursesReport() {
    System.out.println("+------------------+------------------+------------------+------------------+");
    System.out.println("| courseCode       | title            | instructor       | maxCapacity      |");
    System.out.println("+------------------+------------------+------------------+------------------+");
    for (Course course : courses) {
        System.out.printf("| %-16s | %-16s | %-16s | %-16d |\n", course.getCourseCode(), course.getTitle(), course.getInstructor(), course.getMaxCapacity());
    }
    System.out.println("+------------------+------------------+------------------+------------------+");
}


public void studentsInCourseReport(String courseCode) {
    //search coursecode to display all the names of the students enrolled in the course 
    Course course = findCourseByCode(courseCode);

    if (course != null) {
     
        // "EnrolledStudents"array contain all the students who have enrolled in this course.
        List<Student> enrolledStudents = course.getEnrolledStudents();

        if (!enrolledStudents.isEmpty()) {
            // Display
            System.out.println("Students enrolled in the course:");
            for (Student student : enrolledStudents) {
                System.out.println(student.getName());
            }
        } else {
            System.out.println("No students are enrolled in this course.");
        }
    } else {
        System.out.println("Course not found.");
    }
}
/*A table listing all courses at the university. 
Each course is accompanied by the names of all students enrolled in it. */
public void generateAllCoursesAndStudentsReport() {
    System.out.println("+-------------------+-------------------+");
    System.out.println("| Course            | Students          |");
    System.out.println("+-------------------+-------------------+");

    // Traverse through all courses
    for (Course course : courses) {
        String courseName = course.getTitle();

        // Get the students enrolled in the course
        List<Student> enrolledStudents = course.getEnrolledStudents();
        StringBuilder studentsNames = new StringBuilder();

        // Iterate through the students and add their names to the text
        for (Student student : enrolledStudents) {
            studentsNames.append(student.getName()).append(", ");
        }

        // Remove the trailing comma and space at the end of the text
        if (studentsNames.length() > 2) {
            studentsNames.setLength(studentsNames.length() - 2);
        }

        // Display the course name and the names of enrolled students
        System.out.printf("| %-17s | %-17s |\n", courseName, studentsNames.toString());
    }

    System.out.println("+-------------------+-------------------+");
}


}
