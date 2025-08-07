package com.valkyrie.teacher_service.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import com.valkyrie.teacher_service.feign.StudentFeignController;
import com.valkyrie.teacher_service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valkyrie.teacher_service.repository.TeacherRepository;

@Service
public class TeacherService {
    private String[] modifiedName;

    private static final Teacher defaultTeacher = new Teacher().setId("null").setFirstName("null")
            .setSecondName("null").setQualification("null").setAge((byte) 0)
            .setDob(null).setSalary(0).setSubjects(null);
    
    private TeacherRepository repo;
    @Autowired
    private void setRepo(TeacherRepository repo) {this.repo = repo;}

    private StudentFeignController studentFeign;
    @Autowired
    private void setStudentFeign(StudentFeignController studentFeign) {this.studentFeign = studentFeign;}

    private TeacherWrapper getWrapper(Teacher teacher) {

        ResponseEntity<List<StudentWrapper>> students = studentFeign.findByTeacherId(teacher.getId());

        return new TeacherWrapper().setClassTeacher(teacher.getClassTeacher())
                .setAge(teacher.getAge()).setDateOfJoin(teacher.getDateOfJoin())
                .setDob(teacher.getDate()).setImage(teacher.getImage())
                .setFatherFirstName(teacher.getFatherFirstName())
                .setFatherSecondName(teacher.getFatherSecondName())
                .setMotherFirstName(teacher.getMotherFirstName())
                .setMotherSecondName(teacher.getMotherSecondName())
                .setFirstName(teacher.getFirstName()).setSecondName(teacher.getSecondName())
                .setPassOutUniversity(teacher.getPassOutUniversity())
                .setSalary(teacher.getSalary()).setSubjects(teacher.getSubjects())
                .setQualification(teacher.getQualification()).setStudent(
                        students.getStatusCode().equals(HttpStatusCode.valueOf(200))?
                                students.getBody() : null
                );
    }

    private String[] getFirstSecondName(String firstName, String secondName) {

        if (firstName != null && secondName != null) {
            UpperCaseTask taskOne = UpperCaseTask.initialize(firstName);
            UpperCaseTask taskTwo = UpperCaseTask.initialize(secondName);

            Thread threadOne = new Thread(taskOne);
            Thread threadTwo = new Thread(taskTwo);

            threadOne.start();
            threadTwo.start();

            try {
                threadOne.join();
                threadTwo.join();
            } catch (InterruptedException e) {
                threadOne.interrupt();
                threadTwo.interrupt();
                throw new RuntimeException(e);
            }

            if (threadOne.isInterrupted() || threadTwo.isInterrupted()) {
                return new String[]{null};
            }

            return new String[]{taskOne.getWord(), taskTwo.getWord()};
        } else if (firstName != null) {
            UpperCaseTask taskOne = UpperCaseTask.initialize(firstName);

            Thread threadOne = new Thread(taskOne);

            threadOne.start();

            try {
                threadOne.join();
            } catch (InterruptedException e) {
                threadOne.interrupt();
                throw new RuntimeException(e);
            }

            if (threadOne.isInterrupted()) {
                return new String[]{null};
            }

            return new String[]{taskOne.getWord()};
        } else {
            UpperCaseTask taskTwo = UpperCaseTask.initialize(secondName);

            Thread threadTwo = new Thread(taskTwo);

            threadTwo.start();

            try {
                threadTwo.join();
            } catch (InterruptedException e) {
                threadTwo.interrupt();
                throw new RuntimeException(e);
            }

            if (threadTwo.isInterrupted()) {
                return new String[]{null};
            }

            return new String[]{taskTwo.getWord()};
        }
    }

    //save
    public Store<String> save(List<Teacher> teachers) {
        String operationPerformed = "non";

        for (Teacher teacher : teachers) {
            modifiedName = getFirstSecondName(
                    teacher.getFirstName(), teacher.getSecondName()
            );
            String[] modifiedFatherName = getFirstSecondName(
                    teacher.getFatherFirstName(), teacher.getFatherSecondName()
            );
            String[] modifiedMotherName = getFirstSecondName(
                    teacher.getMotherFirstName(), teacher.getMotherSecondName()
            );

            if (modifiedName[0] != null ||
                    modifiedFatherName[0] != null || modifiedMotherName[0] != null) {
                teacher = teacher.setFirstName(modifiedName[0]).setSecondName(modifiedName[1])
                        .setFatherFirstName(modifiedFatherName[0]).setFatherSecondName(modifiedFatherName[1])
                        .setMotherFirstName(modifiedMotherName[0]).setMotherSecondName(modifiedMotherName[1]);
                boolean presentId = teacher.getId() == null;

                if (presentId) {
                    if (operationPerformed.equals("non")) {
                        operationPerformed = "save";
                    }
                    repo.save(teacher.setId(UUID.randomUUID().toString()));
//                return Store.initialize(HttpStatus.ACCEPTED, "The Teacher saved successfully.....");
                } else if (!repo.findById(teacher.getId()).orElse(teacher).toString().equals(teacher.toString())) {
                    if (operationPerformed.equals("non")) {
                        operationPerformed = "update";
                    }
                    repo.save(teacher);
//                return Store.initialize(HttpStatus.ACCEPTED, "Teacher Details has been updated......");
                }
            } else {
                operationPerformed = "non";
            }

        }

        switch (operationPerformed) {
            case "save" -> {
                return Store.initialize(HttpStatus.ACCEPTED, "The Teacher saved successfully.....");
            }
            case "update" -> {
                return Store.initialize(HttpStatus.ACCEPTED, "Teacher Details has been updated......");
            }
            default -> {
                return Store.initialize(HttpStatus.BAD_REQUEST, "Empty data teacher is not allowed");
            }
        }
    }

    //find
    public Store<Teacher> findTeacherById(String id) {
//        id = new String(Base64.getDecoder().decode(id));
        Teacher teacher = repo.findById(id).orElse(null);

        return teacher == null? Store.initialize(HttpStatus.BAD_REQUEST, defaultTeacher) : 
                                Store.initialize(HttpStatus.OK, teacher);
    }

    public Store<List<TeacherWrapper>> findTeachersByName(String firstName, String secondName) {
        modifiedName = getFirstSecondName(firstName, secondName);

        if (modifiedName[0] == null) {
            return Store.initialize(HttpStatus.BAD_REQUEST, null);
        }

        List<Teacher> teachers = repo.findAllByName(modifiedName[0], modifiedName[1]);
        List<TeacherWrapper> teachersList = new ArrayList<>();

        if (teachers.isEmpty()) {
            return Store.initialize(HttpStatus.BAD_REQUEST, teachersList);
        }

        for (Teacher teacher : teachers) {
            teachersList.add(getWrapper(teacher));
        }

        return Store.initialize(HttpStatus.OK, teachersList);
    }

    public Store<List<TeacherWrapper>> findTeachersByFatherName(String fatherFirstName,
                                                         String fatherSecondName) {
        modifiedName = getFirstSecondName(fatherFirstName, fatherSecondName);

        List<Teacher> teachers = new ArrayList<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (fatherFirstName != null && fatherSecondName != null) {
            teachers = repo.findAllByFatherName(modifiedName[0], modifiedName[1]);
            status = HttpStatus.OK;
        } else if (fatherFirstName != null) {
            teachers = repo.findAllByFatherFirstName(modifiedName[0]);
            status = HttpStatus.OK;
        } else if (fatherSecondName != null) {
            teachers = repo.findAllByFatherSecondName(modifiedName[1]);
            status = HttpStatus.OK;
        }

        if (modifiedName[0] == null) {
            return Store.initialize(HttpStatus.BAD_REQUEST, null);
        }

        List<TeacherWrapper> teachersList = new ArrayList<>();

        if (teachers.isEmpty()) {
            return Store.initialize(HttpStatus.BAD_REQUEST, teachersList);
        }

        for (Teacher teacher : teachers) {
            teachersList.add(getWrapper(teacher));
        }

        return Store.initialize(HttpStatus.OK, teachersList);
    }

    public Store<List<TeacherWrapper>> findTeachersByMotherName(String motherFirstName,
                                                         String motherSecondName) {
        modifiedName = getFirstSecondName(motherFirstName, motherSecondName);

        List<Teacher> teachers = new ArrayList<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (motherFirstName != null && motherSecondName != null) {
            teachers = repo.findAllByMotherName(modifiedName[0], modifiedName[1]);
            status = HttpStatus.OK;
        } else if (motherFirstName != null) {
            teachers = repo.findAllByMotherFirstName(modifiedName[0]);
            status = HttpStatus.OK;
        } else if (motherSecondName != null) {
            teachers = repo.findAllByMotherSecondName(modifiedName[1]);
            status = HttpStatus.OK;
        }

        if (modifiedName[0] == null) {
            return Store.initialize(HttpStatus.BAD_REQUEST, null);
        }

        List<TeacherWrapper> teachersList = new ArrayList<>();

        if (teachers.isEmpty()) {
            return Store.initialize(HttpStatus.BAD_REQUEST, teachersList);
        }

        for (Teacher teacher : teachers) {
            teachersList.add(getWrapper(teacher));
        }

        return Store.initialize(HttpStatus.OK, teachersList);
    }

//    public Store<List<Teacher>> findTeachersBySubject(String subject) {
//        List<Teacher> teachers = repo.findAllBySubject(subject);
//
//        return teachers.isEmpty()? Store.initialize(HttpStatus.BAD_REQUEST, List.of(defaultTeacher)) :
//                                    Store.initialize(HttpStatus.OK, teachers);
//    }

    //delete
    public Store<String> removeTeacherById(String id) {
        id = new String(Base64.getDecoder().decode(id));
        
        if (repo.findById(id).orElse(null) == null) {
            return Store.initialize(HttpStatus.OK, "The Teacher with Id = " + id + " has already been removed...");
        }

        repo.deleteById(id);
        
        return repo.findById(id).orElse(null) == null? 
                Store.initialize(
                    HttpStatus.OK, "The teacher with ID = " + id + " has successfully been deleted...") : 
                Store.initialize(
                    HttpStatus.BAD_REQUEST, "The teacher with ID = " + id + " is not deleted...");
    }

//    @Transactional
//    public Store<String> removeTeacherBySubject(String subject) {
//        UpperCaseTask task = UpperCaseTask.initialize(subject);
//        task.run();
//        subject = task.getWord();
//
//        if (repo.findAllBySubject(subject).isEmpty()) {
//            return Store.initialize(HttpStatus.OK, "The Teachers that teaches Subject " +
//                                                    subject + " has already been removed...");
//        }
//
//        repo.deleteAllBySubject(subject);
//
//        return repo.findAllBySubject(subject).isEmpty()?
//                Store.initialize(
//                    HttpStatus.OK, "The Teachers that teaches Subject " +
//                                                    subject + " has been successfully removed...") :
//                Store.initialize(
//                    HttpStatus.BAD_REQUEST, "The Teachers that teaches Subject " +
//                                                    subject + " has not been removed...");
//    }
}
