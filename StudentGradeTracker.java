import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private ArrayList<Double> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public double calculateAverage() {
        if (grades.size() == 0) return 0.0;

        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public void displayGrades() {
        System.out.println("Grades for " + name + ": " + grades);
    }
}

class GradeTracker {
    private ArrayList<Student> students;

    public GradeTracker() {
        students = new ArrayList<>();
    }

    public void addStudent(String name) {
        students.add(new Student(name));
    }

    public Student findStudent(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        System.out.println("All students:");
        for (Student student : students) {
            System.out.println("- " + student.getName());
        }
    }
}

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradeTracker gradeTracker = new GradeTracker();

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("\n2. Add Grade");
            System.out.println("\n3. View Grades");
            System.out.println("\n4. Calculate Average");
            System.out.println("\n5. View All Students");
            System.out.println("\n6. Exit");

            System.out.print("\nChoose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("\nEnter student name: ");
                    String studentName = scanner.nextLine();
                    gradeTracker.addStudent(studentName);
                    break;

                case 2:
                    System.out.print("\nEnter student name: ");
                    studentName = scanner.nextLine();
                    Student student = gradeTracker.findStudent(studentName);
                    if (student != null) {
                        System.out.print("Enter grade: ");
                        double grade = scanner.nextDouble();
                        student.addGrade(grade);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3:
                    System.out.print("\nEnter student name: ");
                    studentName = scanner.nextLine();
                    student = gradeTracker.findStudent(studentName);
                    if (student != null) {
                        student.displayGrades();
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    System.out.print("\nEnter student name: ");
                    studentName = scanner.nextLine();
                    student = gradeTracker.findStudent(studentName);
                    if (student != null) {
                        System.out.println("Average grade: " + student.calculateAverage());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 5:
                    gradeTracker.displayAllStudents();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}
