package org.utm.lab1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;


public class University implements Serializable {

    private static final long serialVersionUID = 1L;

    private transient List<Faculty> faculties;

    public University() {
        faculties = new ArrayList<>();
    }

    public void createFaculty(String facultyName, String facultyAbbreviation, StudyField field) {

        Faculty newFaculty = new Faculty(facultyAbbreviation, field);
        faculties.add(newFaculty);
    }


    public Faculty findFacultyForStudent(String uniqueIdentifier) {

        for (Faculty faculty : faculties) {
            for (Student student : faculty.getStudents()) {

                if (student.getEmail().equals(uniqueIdentifier) || student.getUniqueId().equals(uniqueIdentifier)) {

                    return faculty;
                }
            }
        }
        return null;
    }

    public void displayFaculties() {

        System.out.println("University Faculties:");
        for (Faculty faculty : faculties) {

            System.out.println("Abbreviation: " + faculty.getAbbreviation() + ", Study Field: " + faculty.getStudyField());
        }
    }

    public void displayFacultiesByField(StudyField studyField) {

        System.out.println("Faculties in " + studyField + ":");

        for (Faculty faculty : faculties) {

            if (faculty.getStudyField() == studyField) {
                System.out.println("Abbreviation: " + faculty.getAbbreviation());
            }
        }
    }

    public Faculty findFacultyByAbbreviation(String abbreviation) {

        if (abbreviation != null) {
            for (Faculty faculty : faculties) {
                if (faculty.getAbbreviation().equals(abbreviation)) {

                    return faculty;
                }
            }
        }
        return null;
    }

    public void graduateStudentFromFaculty(String studentUniqueId) {

        Faculty faculty = findFacultyForStudent(studentUniqueId);

        if (faculty != null) {

            Student studentToGraduate = null;
            for (Student student : faculty.getStudents()) {
                if (student.getUniqueId().equals(studentUniqueId)) {

                    studentToGraduate = student;
                    break;
                }
            }

            if (studentToGraduate != null) {

                faculty.graduateStudent(studentToGraduate);
                System.out.println("Student" + studentToGraduate.getFirstName() + " " + studentToGraduate.getLastName() + " graduated from " + faculty.getAbbreviation() + " faculty.");
            } else {

                System.out.println("Student not found in the specified faculty.");
            }
        } else {

            System.out.println("Faculty not found.");
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {

        out.defaultWriteObject();
        out.writeObject(new ArrayList<>(faculties));
    }


    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

        in.defaultReadObject();
        faculties = (List<Faculty>) in.readObject();
    }
}
