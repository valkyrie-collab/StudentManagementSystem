package com.valkyrie.teacher_service.model;

import jakarta.persistence.Embedded;

import java.util.Date;

public class StudentWrapper {
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
    private MarksWrapper marks;
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

    public MarksWrapper getMarks() {return marks;}

    public Date getDob() {return dob;}

    public String getBloodGroup() {return bloodGroup;}

    public Image getImage() {return image;}

    public StudentWrapper setId(String id) {
        this.id = id;
        return this;
    }

    public StudentWrapper setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public StudentWrapper setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public StudentWrapper setFatherFirstName(String fatherFirstName) {
        this.fatherFirstName = fatherFirstName;
        return this;
    }

    public StudentWrapper setFatherSecondName(String fatherSecondName) {
        this.fatherSecondName = fatherSecondName;
        return this;
    }

    public StudentWrapper setMotherFirstName(String motherFirstName) {
        this.motherFirstName = motherFirstName;
        return this;
    }

    public StudentWrapper setEnrolment(Date enrolment) {
        this.enrolment = enrolment;
        return this;
    }

    public StudentWrapper setPassOut(Date passOut) {
        this.passOut = passOut;
        return this;
    }

    public StudentWrapper setStander(String stander) {
        this.stander = stander;
        return this;
    }

    public StudentWrapper setMotherSecondName(String motherSecondName) {
        this.motherSecondName = motherSecondName;
        return this;
    }

    public StudentWrapper setSection(char section) {
        this.section = section;
        return this;
    }

    public StudentWrapper setRole(byte role) {
        this.role = role;
        return this;
    }

    public StudentWrapper setContact(long contact) {
        this.contact = contact;
        return this;
    }

    public StudentWrapper setDob(Date dob) {
        this.dob = dob;
        return this;
    }

    public StudentWrapper setMarks(MarksWrapper marks) {
        this.marks = marks;
        return this;
    }

    public StudentWrapper setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
        return this;
    }

    public StudentWrapper setImage(Image image) {
        this.image = image;
        return this;
    }
}
