package com.valkyrie.teacher_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    private String id;
    private String firstName;
    private String secondName;
    private String qualification;
    private String subject;
    private Date dob;
    private byte age;
    private int salary;

    public String getId() {return id;}

    public String getFirstName() {return firstName;}

    public String getSecondName() {return secondName;}

    public String getQualification() {return qualification;}

    public String getSubject() {return subject;}

    public Date getDate() {return dob;}

    public byte getAge() {return age;}

    public int getSalary() {return salary;}

    public Teacher setId(String id) {
        this.id = id;
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

    public Teacher setSubject(String subject) {
        this.subject = subject;
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
        return id + firstName + qualification + subject + age + salary;
    }
}
