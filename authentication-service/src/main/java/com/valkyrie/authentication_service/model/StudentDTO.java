package com.valkyrie.authentication_service.model;

public class StudentDTO {
    private String role;
    private String password;
    private Student student;

    public String getRole() {return role;}

    public String getPassword() {return password;}

    public Student getStudent() {return student;}

    public StudentDTO setRole(String role) {
        this.role = role;
        return this;
    }

    public StudentDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public StudentDTO setStudent(Student student) {
        this.student = student;
        return this;
    }
}
