package package1;

import java.util.ArrayList;
import java.util.List; 

public class Student {
    private int studentID;
    private String name;
    private String email;

    //All the courses that this student has enrolled in
    private List<Course> enrolledCourses;

    public Student(int studentID, String name, String email) {
        this.studentID = studentID;
        this.name = name;
        this.email = email;
        this.enrolledCourses = new ArrayList<>();
    }
//Getter functions
    public int getStudentID() {
        return studentID;
    }
    
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }



    public void enrollCourse(Course course) {
        /*Here, I took into consideration the case that a student 
        cannot register for more than approximately six courses per semester (the equivalent of 18 hours). */
        if (enrolledCourses.size() < 6) { 
// After checking, the course is added to the 'enrolledCourses' list, which contains all courses student is enrolled in
            enrolledCourses.add(course);
            } else {
            System.out.println("Cannot enroll in more than 6 courses.");
        }
    }

    // To drop a course from the list that contains all the courses the current student is enrolled in, I included it in case it was required in the question.
    // So, there was no need to use it or call it.
    public void dropCourse(Course course) {
        enrolledCourses.remove(course);
        course.dropStudent(this);
    }
}
