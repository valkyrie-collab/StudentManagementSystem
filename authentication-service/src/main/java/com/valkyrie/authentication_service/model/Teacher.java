package com.valkyrie.authentication_service.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

public class Teacher {
    private String id;
    private String email;
    private String firstName;
    private String secondName;
    private String fatherFirstName;
    private String fatherSecondName;
    private String motherFirstName;
    private String motherSecondName;
    private String classTeacher;
    private String qualification;
    private List<String> subjects;
    private Date dateOfJoin;
    private String passOutUniversity;
    private Date dob;
    private byte age;
    private Image image;
    private int salary;

    public String getId() {return id;}

    public String getEmail() {return email;}

    public String getFirstName() {return firstName;}

    public String getSecondName() {return secondName;}

    public String getQualification() {return qualification;}

    public String getFatherFirstName() {return fatherFirstName;}

    public String getFatherSecondName() {return fatherSecondName;}

    public String getMotherFirstName() {return motherFirstName;}

    public String getMotherSecondName() {return motherSecondName;}

    public String getClassTeacher() {return classTeacher;}
    
    public List<String> getSubjects() {return subjects;}
    
    public Date getDateOfJoin() {return dateOfJoin;}

    public String getPassOutUniversity() {return passOutUniversity;}

    public Image getImage() {return image;}

    public Date getDate() {return dob;}

    public byte getAge() {return age;}

    public int getSalary() {return salary;}

    public Teacher setId(String id) {
        this.id = id;
        return this;
    }

    public Teacher setEmail(String email) {
        this.email = email;
        return this;
    }

    public Teacher setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Teacher setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public Teacher setQualification(String qualification) {
        this.qualification = qualification;
        return this;
    }

    public Teacher setFatherFirstName(String fatherFirstName) {
        this.fatherFirstName = fatherFirstName;
        return this;
    }

    public Teacher setFatherSecondName(String fatherSecondName) {
        this.fatherSecondName = fatherSecondName;
        return this;
    }

    public Teacher setMotherFirstName(String motherFirstName) {
        this.motherFirstName = motherFirstName;
        return this;
    }

    public Teacher setMotherSecondName(String motherSecondName) {
        this.motherSecondName = motherSecondName;
        return this;
    }

    public Teacher setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
        return this;
    }

    public Teacher setSubjects(List<String> subjects) {
        this.subjects = subjects;
        return this;
    }

    public Teacher setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
        return this;
    }

    public Teacher setPassOutUniversity(String passOutUniversity) {
        this.passOutUniversity = passOutUniversity;
        return this;
    }

    public Teacher setImage(Image image) {
        this.image = image;
        return this;
    }

    public Teacher setDob(Date dob) {
        this.dob = dob;
        return this;
    }

    public Teacher setAge(byte age) {
        this.age = age;
        return this;
    }

    public Teacher setSalary(int salary) {
        this.salary = salary;
        return this;
    }

    @Override
    public String toString() {
        return id + firstName + qualification +
                subjects + age + salary + fatherFirstName + fatherSecondName +
                motherFirstName + motherSecondName + classTeacher + dateOfJoin + dob + image;
    }
}
