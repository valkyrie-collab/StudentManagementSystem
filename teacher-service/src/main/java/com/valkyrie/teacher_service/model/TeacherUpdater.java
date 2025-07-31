package com.valkyrie.teacher_service.model;

import java.util.List;

public class TeacherUpdater {
    private List<String> ids;
    private List<Teacher> teachers;

    public List<String> getIds() {return ids;}

    public List<Teacher> getTeachers() {return teachers;}

    public TeacherUpdater setIds(List<String> ids) {
        this.ids = ids;
        return this;
    }

    public TeacherUpdater setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
        return this;
    }
}
