package package1;

import java.util.ArrayList;
import java.util.List;

public class Course {
        private String courseCode;
        private String title;
        private String instructor;
        private int maxCapacity;
       
        // all the students who have enrolled in this course.
        private List<Student> enrolledStudents;

        public Course(String courseCode, String title, String instructor, int maxCapacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.instructor = instructor;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new ArrayList<>();}
//Getter function 
    public String getCourseCode() {
        return courseCode;
    }
  /*  public void printCourseCode() {
        System.out.println("Course Code: " + courseCode);
    }*/

    public String getTitle() {
        return title;
    }

    public String getInstructor() {
        return instructor;
    }
    
    public int getMaxCapacity() {
        return maxCapacity;
    }
    
    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    } 
    

//Enrolling a student in the course involves checking whether the section is full or not before the student registers in it.
// Additionally, I checked if the student is already present or not
  public void addStudent(Student student) {
    if (enrolledStudents.size() >= maxCapacity) {
        System.out.println("Course is full. Cannot enroll more students.");
    } else if (enrolledStudents.contains(student)) {
        System.out.println("Student is already enrolled in the course.");
    } else {
// After checking, the student is added to the 'enrolledStudents' list, which contains all the students who have registered for the course
        enrolledStudents.add(student);
//And then the course is added to the second courses list in which the student has registered
        student.enrollCourse(this);
        System.out.println("Student enrolled in the course successfully.");
    }
}



    
//Drop Student from Current Course
    public boolean dropStudent(Student student) {
        if (enrolledStudents.contains(student)) {
//I want to remove the student from the 'enrolledStudents' list because this list contains all the students enrolled in the course.
            enrolledStudents.remove(student);
            return true; 
        } else {
            //The student is not enrolled in the course at all
            return false; 
        }
    }
    

}
