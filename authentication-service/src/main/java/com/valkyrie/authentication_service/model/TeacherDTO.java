package com.valkyrie.authentication_service.model;

public class TeacherDTO {
    private String role;
    private String password;
    private Teacher teacher;

    public String getRole() {return role;}

    public String getPassword() {return password;}

    public Teacher getTeacher() {return teacher;}

    public TeacherDTO setRole(String role) {
        this.role = role;
        return this;
    }

    public TeacherDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public TeacherDTO setStudent(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }
}
