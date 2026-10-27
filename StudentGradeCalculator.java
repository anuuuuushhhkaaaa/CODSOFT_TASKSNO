import java.util.Scanner;

public class StudentGradeCalculator {

    // Method to take a valid positive integer
    public static int getValidInteger(Scanner sc, String message) {
        int number;

        while (true) {
            System.out.print(message);

            try {
                number = Integer.parseInt(sc.nextLine());

                if (number > 0) {
                    return number;
                } else {
                    System.out.println(
                        "Please enter a number greater than 0."
                    );
                }

            } catch (NumberFormatException e) {
                System.out.println(
                    "Invalid input! Please enter a whole number."
                );
            }
        }
    }

    // Method to take marks between 0 and 100
    public static double getValidMarks(
            Scanner sc, String subjectName) {

        double marks;

        while (true) {
            System.out.print(
                "Enter marks in " + subjectName + " (out of 100): "
            );

            try {
                marks = Double.parseDouble(sc.nextLine());

                if (marks >= 0 && marks <= 100) {
                    return marks;
                } else {
                    System.out.println(
                        "Marks must be between 0 and 100."
                    );
                }

            } catch (NumberFormatException e) {
                System.out.println(
                    "Invalid input! Please enter numeric marks."
                );
            }
        }
    }

    // Method to calculate grade
    public static String calculateGrade(double percentage) {

        if (percentage >= 90) {
            return "A+ (Outstanding)";
        } else if (percentage >= 80) {
            return "A (Excellent)";
        } else if (percentage >= 70) {
            return "B (Very Good)";
        } else if (percentage >= 60) {
            return "C (Good)";
        } else if (percentage >= 50) {
            return "D (Average)";
        } else if (percentage >= 40) {
            return "E (Pass)";
        } else {
            return "F (Fail)";
        }
    }

    // Main method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("==================================");
        System.out.println("     STUDENT GRADE CALCULATOR");
        System.out.println("==================================");

        // Input student name
        String studentName;

        do {
            System.out.print("Enter student name: ");
            studentName = sc.nextLine().trim();

            if (studentName.isEmpty()) {
                System.out.println(
                    "Student name cannot be empty."
                );
            }

        } while (studentName.isEmpty());

        // Input number of subjects
        int numberOfSubjects = getValidInteger(
            sc, "Enter number of subjects: "
        );

        String[] subjects = new String[numberOfSubjects];
        double[] marks = new double[numberOfSubjects];

        double totalMarks = 0;

        // Input subject names and marks
        for (int i = 0; i < numberOfSubjects; i++) {

            do {
                System.out.print(
                    "Enter subject " + (i + 1) + " name: "
                );
                subjects[i] = sc.nextLine().trim();

                if (subjects[i].isEmpty()) {
                    System.out.println(
                        "Subject name cannot be empty."
                    );
                }

            } while (subjects[i].isEmpty());

            marks[i] = getValidMarks(sc, subjects[i]);

            totalMarks = totalMarks + marks[i];
        }

        // Calculate maximum marks
        double maximumMarks = numberOfSubjects * 100.0;

        // Calculate average percentage
        double percentage = totalMarks / numberOfSubjects;

        // Calculate grade
        String grade = calculateGrade(percentage);

        // Display results
        System.out.println("\n==================================");
        System.out.println("         STUDENT RESULTS");
        System.out.println("==================================");

        System.out.println("Student Name: " + studentName);

        System.out.println("\nSubject-wise Marks:");

        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.printf(
                "%-20s : %.2f / 100%n",
                subjects[i], marks[i]
            );
        }

        System.out.println("----------------------------------");

        System.out.printf(
            "Total Marks       : %.2f / %.0f%n",
            totalMarks, maximumMarks
        );

        System.out.printf(
            "Average Percentage: %.2f%%%n",
            percentage
        );

        System.out.println("Final Grade       : " + grade);

        System.out.println("==================================");

        sc.close();
    }
}
