
import java.util.*;

public class StudentCourseRegistration {

    static Scanner sc = new Scanner(System.in);

    // Course Database
    static String[] courseCode = {"JAVA101", "DBMS102", "WEB103", "DSA104"};
    static String[] courseTitle = {
        "Core Java",
        "Database Management",
        "Web Development",
        "Data Structures"
    };

    static String[] description = {
        "Java Programming",
        "Database and SQL",
        "HTML CSS and JavaScript",
        "Data Structures and Algorithms"
    };

    static String[] schedule = {
        "Monday 10 AM",
        "Tuesday 11 AM",
        "Wednesday 2 PM",
        "Thursday 9 AM"
    };

    static int[] capacity = {3, 2, 4, 3};
    static int[] registered = {0, 0, 0, 0};

    // Student Database
    static String[] studentIds = new String[100];
    static String[] studentNames = new String[100];
    static int studentCount = 0;

    // Registration Database
    static boolean[][] registrations = new boolean[100][4];

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println(" STUDENT COURSE REGISTRATION SYSTEM");
        System.out.println("====================================");

        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        int studentIndex = findStudent(id);

        if (studentIndex == -1) {
            studentIndex = studentCount;
            studentIds[studentCount] = id;
            studentNames[studentCount] = name;
            studentCount++;
        } else {
            studentNames[studentIndex] = name;
        }

        while (true) {

            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Remove a Course");
            System.out.println("4. View My Registered Courses");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    displayCourses();
                    break;

                case 2:
                    registerCourse(studentIndex);
                    break;

                case 3:
                    removeCourse(studentIndex);
                    break;

                case 4:
                    displayRegisteredCourses(studentIndex);
                    break;

                case 5:
                    System.out.println("Thank you for using the system!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Find student
    static int findStudent(String id) {

        for (int i = 0; i < studentCount; i++) {
            if (studentIds[i].equals(id)) {
                return i;
            }
        }

        return -1;
    }

    // Display available courses
    static void displayCourses() {

        System.out.println("\n========== COURSE LISTING ==========");

        for (int i = 0; i < courseCode.length; i++) {

            System.out.println("\nCourse Code: " + courseCode[i]);
            System.out.println("Course Title: " + courseTitle[i]);
            System.out.println("Description: " + description[i]);
            System.out.println("Schedule: " + schedule[i]);
            System.out.println("Capacity: " + capacity[i]);
            System.out.println("Available Slots: " +
                    (capacity[i] - registered[i]));
        }
    }

    // Register for a course
    static void registerCourse(int studentIndex) {

        displayCourses();

        System.out.print("\nEnter Course Code: ");
        String code = sc.next();

        int index = -1;

        for (int i = 0; i < courseCode.length; i++) {
            if (courseCode[i].equalsIgnoreCase(code)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Course not found!");
        }

        else if (registrations[studentIndex][index]) {
            System.out.println("You are already registered!");
        }

        else if (registered[index] >= capacity[index]) {
            System.out.println("Sorry! Course is full.");
        }

        else {
            registrations[studentIndex][index] = true;
            registered[index]++;

            System.out.println("Course registered successfully!");
        }
    }

    // Remove a registered course
    static void removeCourse(int studentIndex) {

        displayRegisteredCourses(studentIndex);

        System.out.print("\nEnter Course Code to Remove: ");
        String code = sc.next();

        int index = -1;

        for (int i = 0; i < courseCode.length; i++) {
            if (courseCode[i].equalsIgnoreCase(code)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Invalid Course Code!");
        }

        else if (!registrations[studentIndex][index]) {
            System.out.println("You are not registered for this course!");
        }

        else {
            registrations[studentIndex][index] = false;
            registered[index]--;

            System.out.println("Course removed successfully!");
        }
    }

    // Display registered courses
    static void displayRegisteredCourses(int studentIndex) {

        System.out.println("\n========== STUDENT DETAILS ==========");

        System.out.println("Student ID: " + studentIds[studentIndex]);
        System.out.println("Student Name: " + studentNames[studentIndex]);

        System.out.println("\nRegistered Courses:");

        boolean found = false;

        for (int i = 0; i < courseCode.length; i++) {

            if (registrations[studentIndex][i]) {

                System.out.println(
                    courseCode[i] + " - " + courseTitle[i]
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No courses registered yet.");
        }
    }
}
