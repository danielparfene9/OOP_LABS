package org.utm.lab1;

import java.util.Date;

public class Student {

    private String firstName;
    private String lastName;
    private String email;
    private Date enrollmentDate;
    private Date dateOfBirth;
    private String uniqueId;

    public Student(String firstName, String lastName, String email, Date enrollmentDate, Date dateOfBirth, String uniqueId) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
        this.dateOfBirth = dateOfBirth;
        this.uniqueId = uniqueId;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUniqueId() {
        return uniqueId;
    }
}

