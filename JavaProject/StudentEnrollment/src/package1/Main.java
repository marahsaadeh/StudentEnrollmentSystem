package package1;
import java.util.List;
import java.util.Scanner; //For data input by the user

public class Main {
    public static void main(String[] args) {
       System.out.println("Welcome to our student registration system !");
        University university = new University();
        Scanner scanner = new Scanner(System.in);//input user

        while (true) {
            //I used the switch statement because I felt it was the most suitable way to present the requirements.
            System.out.println("___________________________________________________________________________");
            System.out.println("Please enter the service number you would like from the following services:");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students in the university");
            System.out.println("3. Add Course");
            System.out.println("4. Display All Courses in the university");
            System.out.println("5. Enroll Student in Course");
            System.out.println("6. Drop Student from Course");
            System.out.println("7. Reports");
            System.out.println("8. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();//Clear the extra newline character after nextInt()
            //To Ensures that any extra newline characters in the input buffer are cleared
            
            switch (choice) {
            case 1://Add New Student
            System.out.println("Please enter the student information:");
            System.out.print("Student ID: ");
            int studentID = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Student Name: ");
            String name = scanner.nextLine();
            System.out.print("Student Email: ");
            String email = scanner.nextLine();
            //Passing parameters for the variables entered by the user.
            Student newStudent = new Student(studentID, name, email);
            //University class contains an ArrayList that includes all students
            university.addStudent(newStudent);
            break;
            case 2://All students in the university.
            System.out.println("+------------------+------------------+------------------+");
            System.out.println("| Student ID       | Name             | Email            |");
            System.out.println("+------------------+------------------+------------------+");
            //"Walk through the list of student records one by one because each entry represents the data of a single student."
            for (Student student : university.getStudents()) {
                System.out.printf("| %-16d | %-16s | %-16s |\n", student.getStudentID(), student.getName(), student.getEmail());
            }
            System.out.println("+------------------+------------------+------------------+");
            break;
            case 3://Add New course
            System.out.println("Please enter the course information:");
            System.out.print("Course Code: ");
            String courseCode = scanner.nextLine();// Course Code string 
            System.out.print("Course Title: ");
            String courseTitle = scanner.nextLine();
            System.out.print("Instructor: ");
            String instructor = scanner.nextLine();
            System.out.print("Maximum Capacity: ");
            int maxCapacity = scanner.nextInt();
            scanner.nextLine(); //Clear the extra newline character after nextInt()
            //To Ensures that any extra newline characters in the input buffer are cleared
            Course newCourse = new Course(courseCode, courseTitle, instructor, maxCapacity);
            //University class contains an ArrayList that includes all Courses
            university.addCourse(newCourse);
            break;

            case 4://Display All Courses in the university
            //I put it in a function because the code will be repeated again in case 7 (report).
            university.allCoursesReport();
            break;

            case 5://Enrolling the student in the course
            System.out.print("Enter Student ID: ");
            int studentId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Course Code: ");
            String courseCodeStudent = scanner.next();

            //searching studentId within the array students 
            Student studentIsExist = university.findStudentByID(studentId);
            if (studentIsExist != null) {
            //As long as the student is present in the array,let's search for the course entered by the user within the array Courses
            Course course = university.findCourseByCode(courseCodeStudent);
            if (course != null) {
            //As long as the course is present.Now you can enroll the student in the course
            course.addStudent(studentIsExist);           
            } else {
                System.out.println("Course not found."); }} 
            else {
                System.out.println("Student not found.");}
            break;


            case 6://Drop Student from Course
//Which student do you want to drop, and from which course do you want to drop them
            System.out.print("Enter Student ID: ");
            int studentIdDrop = scanner.nextInt();
            System.out.print("Enter Course Code: ");
            String courseCodeDrop = scanner.next();

            //searching studentId within the array students 
            Student student = university.findStudentByID(studentIdDrop);
            if (student != null) {
//As long as the student is present in the array,let's search for the course entered by the user within the array Courses
                Course course = university.findCourseByCode(courseCodeDrop);
                if (course != null) {
                 //As long as the course is present.Now withdraw the student from the course
                    if (course.dropStudent(student)) {
                        System.out.println("Student dropped from the course successfully.");
                    } else {
                        System.out.println("Student is not enrolled in the course.");
                    }
                } else {
                    System.out.println("Course not found.");
                } } else {
                System.out.println("Student not found.");}
            break;

            case 7: //Reports
            System.out.println("Please select the report number you would like from the following:");
            System.out.println("1. All available courses in the university");
            System.out.println("2. Courses for a Specific Student");
            System.out.println("3. Students in a Specific Course");
            System.out.println("4. Report of All");
            int reportChoice = scanner.nextInt();
            switch (reportChoice) {
                case 1: //Report All courses in the university.
                university.allCoursesReport();
           break;
           
            case 2: // All the courses that student has enrolled in
            // Ask the user for the student's ID
            System.out.print("Enter Student ID: ");
            int studentIdCourses = scanner.nextInt();
         //searching studentId within the array students 
            Student studentRep = university.findStudentByID(studentIdCourses);
            if (studentRep  != null) {
// Getting the courses in which the student is enrolledFrom the array EnrolledCourses 
//because this array contains all the courses in which the current student is registered.
                List<Course> enrolledCourses = studentRep .getEnrolledCourses();
                if (!enrolledCourses.isEmpty()) {
                    //Display the courses that the student is enrolled in
                    System.out.println("Courses enrolled by the student:");
                    for (Course course : enrolledCourses) {
                        System.out.println(course.getCourseCode() + ": " + course.getTitle());
                    }
                } else {
                    System.out.println("The student is not enrolled in any courses.");
                }
            } else {
                System.out.println("Student not found.");
            }
                    break;
             case 3:
//Give me the Course Code to display the names of the students who are enrolled in it.
                    System.out.print("Enter Course Code: ");
                    String courseCodeNameS = scanner.next();
                    university.studentsInCourseReport(courseCodeNameS);
                    break;
            case 4: //All Repo
                System.out.println("Report of all available courses in the university:");
                university.allCoursesReport();
                System.out.println("\nReport of courses for every students:");
                for (Student allStudent : university.getStudents()) {
                    System.out.println("Student ID: " + allStudent.getStudentID());
                    List<Course> enrolledCourses = allStudent.getEnrolledCourses();
                    if (!enrolledCourses.isEmpty()) {
                        System.out.println("Enrolled Courses:");
                        for (Course allCourse : enrolledCourses) {
                            System.out.println(allCourse.getCourseCode() + ": " + allCourse.getTitle());
                        }
                    } else {
                        System.out.println("No courses enrolled for this student.");
                    }
                    System.out.println();

                } 
             System.out.println("Report of All Courses and Enrolled Students:");
            university.generateAllCoursesAndStudentsReport();
                break;
                default:
                    System.out.println("Invalid report choice. Please select a valid option.");
                    break;
            }
            break;
            case 8:
            System.out.println("Exit the program.");
            return;
            default:
            System.out.println("Invalid choice. Please select a valid option.");
            scanner.close();
            }
           
        }
       
        
    }

/*      
        //Default Data to test
        University university = new University();
        System.out.println("----------------------------------------");
        Student student1 = new Student(1, "Mara", "marah@gmail.com");
        Student student2 = new Student(2, "Ali", "ali@egmail.com");
        Course course1 = new Course("CS11", "Introduction to Programming", "Dr. ahmad", 2);
        Course course2 = new Course("MATH102", "Calculus II", "Dr. tamer", 1);
        Course course3 = new Course("MATH103", "Calculus I", "Dr. lma", 56);
        Course course4 = new Course("MATH104", "Calculus III", "Dr. adam", 4);

        university.addStudent(student1);
        university.addStudent(student2);
        university.addCourse(course1);
        university.addCourse(course2);
        university.addCourse(course3);
        university.addCourse(course4);

        student1.enrollCourse(course1);
        student1.enrollCourse(course2);
        student2.enrollCourse(course1);

*/

    }
