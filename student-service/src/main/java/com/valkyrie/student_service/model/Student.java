package com.valkyrie.student_service.model;

//import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;
//import java.util.List;

@Entity
@Table (name = "student")
public class Student {
    @Id
    private String id;
    private String firstName;
    private String secondName;
    private String fatherFirstName;
    private String fatherSecondName;
    private String motherFirstName;
    private String motherSecondName;
    private Date enrolment;
    private Date passOut;
    private String stander;
    private char section;
    private byte role;
    private long contact;
    private String subjectId;
    private Date dob;
    private String bloodGroup;
    @Embedded //will become column in the same table
    private Image image;

    public String getId() {return id;}

    public String getFirstName() {return firstName;}

    public String getSecondName() {return secondName;}

    public String getFatherFirstName() {return fatherFirstName;}

    public String getFatherSecondName() {return fatherSecondName;}

    public String getMotherFirstName() {return motherFirstName;}

    public String getMotherSecondName() {return motherSecondName;}

    public Date getEnrolment() {return enrolment;}

    public Date getPassOut() {return passOut;}

    public String getStander() {return stander;}

    public char getSection() {return section;}

    public byte getRole() {return role;}

    public long getContact() {return contact;}

    public String getSubjectId() {return subjectId;}

    public Date getDob() {return dob;}

    public String getBloodGroup() {return bloodGroup;}

    public Image getImage() {return image;}

    public Student setId(String id) {
        this.id = id;
        return this;
    }

    public Student setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Student setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public Student setFatherFirstName(String fatherFirstName) {
        this.fatherFirstName = fatherFirstName;
        return this;
    }

    public Student setFatherSecondName(String fatherSecondName) {
        this.fatherSecondName = fatherSecondName;
        return this;
    }

    public Student setMotherFirstName(String motherFirstName) {
        this.motherFirstName = motherFirstName;
        return this;
    }

    public Student setEnrolment(Date enrolment) {
        this.enrolment = enrolment;
        return this;
    }

    public Student setPassOut(Date passOut) {
        this.passOut = passOut;
        return this;
    }

    public Student setStander(String stander) {
        this.stander = stander;
        return this;
    }

    public Student setMotherSecondName(String motherSecondName) {
        this.motherSecondName = motherSecondName;
        return this;
    }

    public Student setSection(char section) {
        this.section = section;
        return this;
    }

    public Student setRole(byte role) {
        this.role = role;
        return this;
    }

    public Student setContact(long contact) {
        this.contact = contact;
        return this;
    }

    public Student setDob(Date dob) {
        this.dob = dob;
        return this;
    }

    public Student setSubjectId(String subjectId) {
        this.subjectId = subjectId;
        return this;
    }

    public Student setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
        return this;
    }

    public Student setImage(Image image) {
        this.image = image;
        return this;
    }

    @Override
    public String toString() {
        return id + firstName + secondName + fatherFirstName + fatherSecondName +
                motherFirstName + motherSecondName + enrolment + passOut +
                stander + section + role + contact + subjectId + dob + bloodGroup;
    }
}
