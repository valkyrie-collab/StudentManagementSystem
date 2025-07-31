package com.valkyrie.teacher_service.service;

import java.util.List;
import java.util.UUID;

import com.valkyrie.teacher_service.model.UpperCaseTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valkyrie.teacher_service.model.Teacher;
import com.valkyrie.teacher_service.model.Store;
import com.valkyrie.teacher_service.repository.TeacherRepository;

@Service
public class TeacherService {
    private static final Teacher defaultTeacher = new Teacher().setId("null").setFirstName("null")
            .setSecondName("null").setQualification("null").setAge((byte) 0)
            .setDob(null).setSalary(0).setSubject("null");
    
    private TeacherRepository repo;
    @Autowired
    private void setRepo(TeacherRepository repo) {this.repo = repo;}

    //save
    public Store<String> save(List<Teacher> teachers) {
        String operationPerformed = "non";

        for (Teacher teacher : teachers) {
            UpperCaseTask firstName = UpperCaseTask.initialize(teacher.getFirstName());
            UpperCaseTask secondName = UpperCaseTask.initialize(teacher.getSecondName());
            UpperCaseTask subject = UpperCaseTask.initialize(teacher.getSubject());

            Thread taskNumberOne = new Thread(firstName);
            Thread taskNumberTwo = new Thread(secondName);
            Thread taskNumberThree = new Thread(subject);

            taskNumberOne.start(); taskNumberTwo.start(); taskNumberThree.start();

            try {
                taskNumberOne.join();
                taskNumberTwo.join();
                taskNumberThree.join();
            } catch (InterruptedException ie) {
                taskNumberOne.interrupt();
                taskNumberTwo.interrupt();
                taskNumberThree.interrupt();
                ie.printStackTrace();
            }

            if (!taskNumberOne.isInterrupted() ||
                    !taskNumberTwo.isInterrupted() || !taskNumberThree.isInterrupted()) {
                teacher = teacher.setFirstName(firstName.getWord())
                        .setSecondName(secondName.getWord()).setSubject(subject.getWord());
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
        Teacher teacher = repo.findById(id).orElse(null);

        return teacher == null? Store.initialize(HttpStatus.BAD_REQUEST, defaultTeacher) : 
                                Store.initialize(HttpStatus.OK, teacher);
    }

    public Store<List<Teacher>> findTeachersByFirstName(String firstName) {
        UpperCaseTask task = UpperCaseTask.initialize(firstName);
        task.run();
        List<Teacher> teachers = repo.findAllByFirstName(task.getWord());

        return teachers.isEmpty()? Store.initialize(HttpStatus.BAD_REQUEST, List.of(defaultTeacher)) : 
                                    Store.initialize(HttpStatus.OK, teachers);
    }

    public Store<List<Teacher>> findTeachersBySecondName(String secondName) {
        UpperCaseTask task = UpperCaseTask.initialize(secondName);
        task.run();
        List<Teacher> teachers = repo.findAllBySecondName(secondName);

        return teachers.isEmpty()? Store.initialize(HttpStatus.BAD_REQUEST, List.of(defaultTeacher)) :
                Store.initialize(HttpStatus.OK, teachers);
    }

    public Store<List<Teacher>> findTeachersByName(String firstName, String secondName) {
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

        if (!threadOne.isInterrupted() || !threadTwo.isInterrupted()) {
            List<Teacher> teachers = repo.findAllByName(firstName, secondName);

            return teachers.isEmpty() ? Store.initialize(HttpStatus.BAD_REQUEST, List.of(defaultTeacher)) :
                    Store.initialize(HttpStatus.OK, teachers);
        } else {
            return Store.initialize(HttpStatus.BAD_REQUEST, List.of(defaultTeacher));
        }
    }

    public Store<List<Teacher>> findTeachersBySubject(String subject) {
        List<Teacher> teachers = repo.findAllBySubject(subject);

        return teachers.isEmpty()? Store.initialize(HttpStatus.BAD_REQUEST, List.of(defaultTeacher)) : 
                                    Store.initialize(HttpStatus.OK, teachers);
    }

    //delete
    public Store<String> removeTeacherById(String id) {
        
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

    @Transactional
    public Store<String> removeTeacherBySubject(String subject) {
        UpperCaseTask task = UpperCaseTask.initialize(subject);
        task.run();
        subject = task.getWord();

        if (repo.findAllBySubject(subject).isEmpty()) {
            return Store.initialize(HttpStatus.OK, "The Teachers that teaches Subject " + 
                                                    subject + " has already been removed...");
        }

        repo.deleteAllBySubject(subject);

        return repo.findAllBySubject(subject).isEmpty()? 
                Store.initialize(
                    HttpStatus.OK, "The Teachers that teaches Subject " + 
                                                    subject + " has been successfully removed...") : 
                Store.initialize(
                    HttpStatus.BAD_REQUEST, "The Teachers that teaches Subject " + 
                                                    subject + " has not been removed...");
    }
}
