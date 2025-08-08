package com.valkyrie.teacher_service.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;

import java.util.Date;
import java.util.List;

public class TeacherWrapper {
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
    private List<StudentWrapper> students;

    public String getFirstName() {return firstName;}

    public String getSecondName() {return secondName;}

    public String getQualification() {return qualification;}

    public String getFatherFirstName() {return fatherFirstName;}

    public String getFatherSecondName() {return fatherSecondName;}

    public String getMotherFirstName() {return motherFirstName;}

    public String getPassOutUniversity() {return passOutUniversity;}

    public String getMotherSecondName() {return motherSecondName;}

    public String getClassTeacher() {return classTeacher;}

    public List<String> getSubjects() {return subjects;}

    public Date getDateOfJoin() {return dateOfJoin;}

    public List<StudentWrapper> getStudent() {return students;}

    public Image getImage() {return image;}

    public Date getDob() {return dob;}

    public byte getAge() {return age;}

    public int getSalary() {return salary;}

    public TeacherWrapper setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public TeacherWrapper setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public TeacherWrapper setQualification(String qualification) {
        this.qualification = qualification;
        return this;
    }

    public TeacherWrapper setFatherFirstName(String fatherFirstName) {
        this.fatherFirstName = fatherFirstName;
        return this;
    }

    public TeacherWrapper setFatherSecondName(String fatherSecondName) {
        this.fatherSecondName = fatherSecondName;
        return this;
    }

    public TeacherWrapper setMotherFirstName(String motherFirstName) {
        this.motherFirstName = motherFirstName;
        return this;
    }

    public TeacherWrapper setMotherSecondName(String motherSecondName) {
        this.motherSecondName = motherSecondName;
        return this;
    }

    public TeacherWrapper setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
        return this;
    }

    public TeacherWrapper setSubjects(List<String> subjects) {
        this.subjects = subjects;
        return this;
    }

    public TeacherWrapper setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
        return this;
    }

    public TeacherWrapper setPassOutUniversity(String passOutUniversity) {
        this.passOutUniversity = passOutUniversity;
        return this;
    }

    public TeacherWrapper setImage(Image image) {
        this.image = image;
        return this;
    }

    public TeacherWrapper setDob(Date dob) {
        this.dob = dob;
        return this;
    }

    public TeacherWrapper setAge(byte age) {
        this.age = age;
        return this;
    }

    public TeacherWrapper setSalary(int salary) {
        this.salary = salary;
        return this;
    }

    public TeacherWrapper setStudent(List<StudentWrapper> students) {
        this.students = students;
        return this;
    }
}
