import java.util.ArrayList;
import java.util.Scanner;

public class GradeTracker {
    private static class Student {
        String name;
        ArrayList<Integer> grades;

        Student(String name) {
            this.name = name;
            this.grades = new ArrayList<>();
        }

        void addGrade(int grade) {
            grades.add(grade);
        }

        double getAverage() {
            if (grades.isEmpty()) return 0;
            int sum = 0;
            for (int g : grades) {
                sum += g;
            }
            return (double) sum / grades.size();
        }

        int getHighest() {
            if (grades.isEmpty()) return 0;
            int max = grades.get(0);
            for (int g : grades) {
                if (g > max) max = g;
            }
            return max;
        }

        int getLowest() {
            if (grades.isEmpty()) return 0;
            int min = grades.get(0);
            for (int g : grades) {
                if (g < min) min = g;
            }
            return min;
        }
    }

    private ArrayList<Student> students;
    private Scanner scanner;

    public GradeTracker() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Welcome to the Student Grade Tracker");
        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Add student");
            System.out.println("2. Add grade to student");
            System.out.println("3. Display summary report");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addGradeToStudent();
                    break;
                case 3:
                    displaySummary();
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        students.add(new Student(name));
        System.out.println("Student added.");
    }

    private void addGradeToStudent() {
        if (students.isEmpty()) {
            System.out.println("No students available. Add students first.");
            return;
        }
        System.out.println("Select student:");
        for (int i = 0; i < students.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, students.get(i).name);
        }
        System.out.print("Enter student number: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline
        if (index < 0 || index >= students.size()) {
            System.out.println("Invalid student number.");
            return;
        }
        System.out.print("Enter grade (0-100): ");
        int grade = scanner.nextInt();
        scanner.nextLine(); // consume newline
        if (grade < 0 || grade > 100) {
            System.out.println("Invalid grade.");
            return;
        }
        students.get(index).addGrade(grade);
        System.out.println("Grade added.");
    }

    private void displaySummary() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("\nSummary Report:");
        for (Student s : students) {
            System.out.printf("Student: %s%n", s.name);
            System.out.printf("Grades: %s%n", s.grades);
            System.out.printf("Average: %.2f%n", s.getAverage());
            System.out.printf("Highest: %d%n", s.getHighest());
            System.out.printf("Lowest: %d%n", s.getLowest());
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GradeTracker app = new GradeTracker();
        app.run();
    }
}
