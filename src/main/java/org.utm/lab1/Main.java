package org.utm.lab1;

import java.util.Scanner;
import java.util.Date;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        University university = new University();
        SaveManager saveManager = new SaveManager();

        System.out.println("Welcome to TUM's student management system!");
        university = saveManager.loadUniversity();

        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("g - General operations");
            System.out.println("f - Faculty operations");
            System.out.println("s - Student operations");
            System.out.println("q - Quit program");
            System.out.print("Your input> ");
            String choice = scanner.nextLine();

            if (choice.equals("q")) {
                saveManager.saveUniversity(university);
                break;
            } else if (choice.equals("g")) {
                while (true) {
                    System.out.println("General operations,");
                    System.out.println("What would you like to do?");
                    System.out.println("nf/<faculty name>/<faculty abbreviation>/<field> - create faculty");
                    System.out.println("ss/<student email> or ss/<student uniqueID> - search student and his faculty");
                    System.out.println("df/ - display faculties");
                    System.out.println("df/<field> - display all faculties of a field");
                    System.out.println("b - back");
                    System.out.println("q - Quit Program");
                    System.out.print("Your input> ");
                    String generalChoice = scanner.nextLine();

                    if (generalChoice.equals("b")) {
                        break;
                    } else if (generalChoice.startsWith("nf/")) {
                        String[] parts = generalChoice.split("/");
                        if (parts.length == 4) {
                            String facultyName = parts[1];
                            String facultyAbbreviation = parts[2];
                            StudyField field = StudyField.valueOf(parts[3]);
                            university.createFaculty(facultyName, facultyAbbreviation, field);
                            System.out.println("Faculty created.");
                        } else {
                            System.out.println("Invalid input for creating faculty.");
                        }
                    } else if (generalChoice.startsWith("ss/")) {
                        String[] parts = generalChoice.split("/");
                        if (parts.length == 2) {
                            String uniqueIdentifier = parts[1];
                            Faculty faculty = university.findFacultyForStudent(uniqueIdentifier);
                            if (faculty != null) {
                                System.out.println("Student belongs to " + faculty.getAbbreviation() + " faculty.");
                            } else {
                                System.out.println("Student not found.");
                            }
                        } else {
                            System.out.println("Invalid input for searching student.");
                        }
                    } else if (generalChoice.equals("df/")) {
                        university.displayFaculties();
                    } else if (generalChoice.startsWith("df/")) {
                        String[] parts = generalChoice.split("/");
                        if (parts.length == 2) {
                            StudyField field = StudyField.valueOf(parts[1]);
                            university.displayFacultiesByField(field);
                        } else {
                            System.out.println("Invalid input for displaying faculties of a field.");
                        }
                    } else {
                        System.out.println("Invalid choice.");
                    }
                }



                // FACULTY OPERATIONS START HERE/////////////////////////////////////////////////////////////////////////////////
            } else if (choice.equals("f")) {
                while (true) {
                    System.out.println("Faculty operations,");
                    System.out.println("What would you like to do?");
                    System.out.println("cfs/<faculty abbreviation>/<student first name>/<student last name>/<student email>/<student uniqueID> - create and assign student to faculty");
                    System.out.println("gs/<student uniqueID> - graduate student from a faculty");
                    System.out.println("dce/<faculty abbreviation> - display current enrolled students in a faculty");
                    System.out.println("dg/<faculty abbreviation> - display graduates from a faculty");
                    System.out.println("sbf/<faculty abbreviation>/<student uniqueID> - check if a student belongs to a faculty");
                    System.out.println("b - back");
                    System.out.println("q - Quit Program");
                    System.out.print("Your input> ");
                    String facultyChoice = scanner.nextLine();

                    if (facultyChoice.equals("b")) {
                        break;
                    } else if (facultyChoice.startsWith("cfs/")) {
                        String[] parts = facultyChoice.split("/");
                        if (parts.length == 6) {
                            String facultyAbbreviation = parts[1];
                            String studentFirstName = parts[2];
                            String studentLastName = parts[3];
                            String studentEmail = parts[4];
                            String studentUniqueId = parts[5];


                            Student student = new Student(studentFirstName, studentLastName, studentEmail, new Date(), new Date(), studentUniqueId);


                            Faculty faculty = university.findFacultyByAbbreviation(facultyAbbreviation);

                            if (faculty != null) {

                                faculty.enrollStudent(student);
                                System.out.println("Student enrolled in " + facultyAbbreviation + " faculty.");
                            } else {
                                System.out.println("Faculty not found.");
                            }
                        } else {
                            System.out.println("Invalid input for creating and assigning a student to a faculty.");
                        }
                    } else if (facultyChoice.startsWith("gs/")) {
                        String[] parts = facultyChoice.split("/");
                        if (parts.length == 2) {
                            String studentUniqueId = parts[1];


                            Faculty faculty = university.findFacultyForStudent(studentUniqueId);

                            if (faculty != null) {

                                university.graduateStudentFromFaculty(studentUniqueId);
                                System.out.println("Student graduated from " + faculty.getAbbreviation() + " faculty.");
                            } else {
                                System.out.println("Student not found in any faculty.");
                            }
                        } else {
                            System.out.println("Invalid input for graduating a student.");
                        }
                    } else if (facultyChoice.startsWith("dce/")) {
                        String[] parts = facultyChoice.split("/");
                        if (parts.length == 2) {
                            String facultyAbbreviation = parts[1];


                            Faculty faculty = university.findFacultyByAbbreviation(facultyAbbreviation);

                            if (faculty != null) {
                                faculty.displayCurrentStudents();
                            } else {
                                System.out.println("Faculty not found.");
                            }
                        } else {
                            System.out.println("Invalid input for displaying current enrolled students.");
                        }
                    } else if (facultyChoice.startsWith("dg/")) {
                        String[] parts = facultyChoice.split("/");
                        if (parts.length == 2) {
                            String facultyAbbreviation = parts[1];


                            Faculty faculty = university.findFacultyByAbbreviation(facultyAbbreviation);

                            if (faculty != null) {
                                faculty.displayGraduates(faculty.getGraduateStudents());
                            } else {
                                System.out.println("Faculty not found.");
                            }
                        } else {
                            System.out.println("Invalid input for displaying graduates.");
                        }
                    } else if (facultyChoice.startsWith("sbf/")) {
                        String[] parts = facultyChoice.split("/");
                        if (parts.length == 3) {
                            String facultyAbbreviation = parts[1];
                            String studentUniqueId = parts[2];


                            Faculty faculty = university.findFacultyByAbbreviation(facultyAbbreviation);

                            if (faculty != null) {

                                boolean isStudentInFaculty = faculty.isStudentInFaculty(studentUniqueId);
                                if (isStudentInFaculty) {
                                    System.out.println("Student with unique ID " + studentUniqueId + " belongs to " + facultyAbbreviation + " faculty.");
                                } else {
                                    System.out.println("Student with unique ID " + studentUniqueId + " does not belong to " + facultyAbbreviation + " faculty.");
                                }
                            } else {
                                System.out.println("Faculty not found.");
                            }
                        } else {
                            System.out.println("Invalid input for checking if a student belongs to a faculty.");
                        }
                    } else {
                        System.out.println("Invalid choice.");
                    }
                }
            } else if (choice.equals("s")) {

            } else {
                System.out.println("Invalid choice.");
            }
        }

        System.out.println("Program quit.");
    }
}



