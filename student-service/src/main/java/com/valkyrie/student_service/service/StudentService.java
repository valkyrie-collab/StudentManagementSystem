package com.valkyrie.student_service.service;

import com.valkyrie.student_service.model.Student;
import com.valkyrie.student_service.model.UpperCaseTask;
import com.valkyrie.student_service.repository.StudentRepository;
import com.valkyrie.student_service.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    private static final Student defaultStudent = new Student().setId("null").setFirstName("null")
            .setSecondName("null").setFatherFirstName("null").setFatherSecondName("null").setImage(null)
            .setEnrolment(null).setFatherSecondName("null").setMotherFirstName("null").setMotherSecondName("null")
            .setPassOut(null).setDob(null).setRole((byte)-1).setContact(-1L).setSection('N').setStander("null");
    private StudentRepository repo;
    @Autowired
    private void setRepo(StudentRepository repo) {this.repo = repo;}

    public Store<List<String>> save(List<Student> students) {
        List<String> message = new ArrayList<>(students.size());

        for (Student student : students) {
            boolean check = student.getId() == null;

            UpperCaseTask taskStudentFirstName = UpperCaseTask.initialize(student.getFirstName());
            UpperCaseTask taskStudentSecondName = UpperCaseTask.initialize(student.getSecondName());
            UpperCaseTask taskStudentFatherFirstName = UpperCaseTask.initialize(student.getFatherFirstName());
            UpperCaseTask taskStudentFatherSecondName = UpperCaseTask.initialize(student.getFatherSecondName());
            UpperCaseTask taskStudentMotherFirstName = UpperCaseTask.initialize(student.getMotherFirstName());
            UpperCaseTask taskStudentMotherSecondName = UpperCaseTask.initialize(student.getMotherSecondName());

            Thread threadStudentFirstName = new Thread(taskStudentFirstName);
            Thread threadStudentSecondName = new Thread(taskStudentSecondName);
            Thread threadStudentFatherFirstName = new Thread(taskStudentFatherFirstName);
            Thread threadStudentFatherSecondName = new Thread(taskStudentFatherSecondName);
            Thread threadStudentMotherFirstName = new Thread(taskStudentMotherFirstName);
            Thread threadStudentMotherSecondName = new Thread(taskStudentMotherSecondName);

            threadStudentFirstName.start();
            threadStudentSecondName.start();
            threadStudentFatherFirstName.start();
            threadStudentFatherSecondName.start();
            threadStudentMotherFirstName.start();
            threadStudentMotherSecondName.start();

            try {
                threadStudentFirstName.join();
                threadStudentSecondName.join();
                threadStudentFatherFirstName.join();
                threadStudentFatherSecondName.join();
                threadStudentMotherFirstName.join();
                threadStudentMotherSecondName.join();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(ie);
            }

            student = student.setFirstName(taskStudentFirstName.getWord())
                    .setSecondName(taskStudentSecondName.getWord())
                    .setFatherFirstName(taskStudentFatherFirstName.getWord())
                    .setFatherSecondName(taskStudentFatherSecondName.getWord())
                    .setMotherFirstName(taskStudentMotherFirstName.getWord())
                    .setMotherSecondName(taskStudentMotherSecondName.getWord());

            if (check) {
                student = student.setId(UUID.randomUUID().toString());
                repo.save(student);
                message.add("The Student With ID = " + student.getId() + " has been added successfully...");
            } else if (!repo.findById(student.getId()).orElse(student).toString().equals(student.toString())) {
                repo.save(student);
                message.add("The Student With ID = " + student.getId() + " has been updated successfully...");
            } else {
                message.add("The Student not updated/saved....");
            }
        }

        return !students.isEmpty()? Store.initialize(HttpStatus.ACCEPTED, message) :
                Store.initialize(HttpStatus.BAD_REQUEST, new ArrayList<>());
    }

    //Find Student
    @Transactional
    public Store<Student> findStudentById(String id) {
        Student student = repo.findById(id).orElse(null);

        return student == null? Store.initialize(HttpStatus.BAD_REQUEST, defaultStudent) :
                Store.initialize(HttpStatus.OK, student);
    }

    @Transactional
    public Store<List<Student>> findStudentByName(String firstName, String secondName) {
        List<Student> students = new ArrayList<>();

        if (firstName != null && secondName != null) {
            students = repo.findAllByName(firstName, secondName);
        } else if (firstName != null) {
            students = repo.findAllByFirstName(firstName);
        } else if (secondName != null) {
            students = repo.findAllBySecondName(secondName);
        } else {
            return Store.initialize(HttpStatus.BAD_REQUEST, List.of(defaultStudent));
        }

        return !students.isEmpty()? Store.initialize(HttpStatus.OK, students) :
                Store.initialize(HttpStatus.BAD_REQUEST, List.of(defaultStudent));
    }

    @Transactional
    public Store<List<Student>> findStudentByFatherName(String fatherFirstName,
                                                        String fatherSecondName) {
        List<Student> students = new ArrayList<>();

        if (fatherFirstName != null && fatherSecondName != null) {
            students = repo.findAllByFatherName(fatherFirstName, fatherSecondName);
        } else if (fatherFirstName != null) {
            students = repo.findAllByFatherFirstName(fatherFirstName);
        } else if (fatherSecondName != null) {
            students = repo.findAllByFatherSecondName(fatherSecondName);
        } else {
            return Store.initialize(HttpStatus.BAD_REQUEST, List.of(defaultStudent));
        }

        return !students.isEmpty()? Store.initialize(HttpStatus.OK, students) :
                Store.initialize(HttpStatus.BAD_REQUEST, List.of(defaultStudent));
    }

    @Transactional
    public Store<List<Student>> findStudentByMotherName(String motherFirstName,
                                                        String motherSecondName) {
        List<Student> students = new ArrayList<>();

        if (motherFirstName != null && motherSecondName != null) {
            students = repo.findAllByMotherName(motherFirstName, motherSecondName);
        } else if (motherFirstName != null) {
            students = repo.findAllByMotherFirstName(motherFirstName);
        } else if (motherSecondName != null) {
            students = repo.findAllByMotherSecondName(motherSecondName);
        } else {
            return Store.initialize(HttpStatus.BAD_REQUEST, List.of(defaultStudent));
        }

        return !students.isEmpty()? Store.initialize(HttpStatus.OK, students) :
                Store.initialize(HttpStatus.BAD_REQUEST, List.of(defaultStudent));
    }

    @Transactional
    public Store<List<Student>> findStudentByStanderAndSection(String stander, char section) {
        List<Student> students = new ArrayList<>();

        if (stander != null && section < 'E') {
            students = repo.findAllByStanderAndSection(stander, section);
        } else if (stander != null) {
            students = repo.findAllByStander(stander);
        } else if (section < 'E') {
            students = repo.findAllBySection(section);
        } else {
            return Store.initialize(HttpStatus.BAD_REQUEST, List.of(defaultStudent));
        }

        return !students.isEmpty()? Store.initialize(HttpStatus.OK, students) :
                Store.initialize(HttpStatus.BAD_REQUEST, List.of(defaultStudent));
    }

    //DeleteStudent
    public Store<String> deleteStudentById(String id) {

        if (repo.findById(id).orElse(null) == null) {
            return Store.initialize(HttpStatus.OK,
                    "The Student with Id = " + id + " has already been deleted....");
        }

        repo.deleteById(id);

        return repo.findById(id).orElse(null) == null?
                Store.initialize(HttpStatus.OK,
                        "The Student With ID = " + id + " has been deleted successfully......") :
                Store.initialize(HttpStatus.BAD_REQUEST,
                        "The Student with ID = " + id + " has not been deleted successfully.....");
    }

    @Transactional
    public Store<String> deleteStudentsByStanderAndSection(String stander, char section) {

        if (repo.findAllByStanderAndSection(stander, section).isEmpty()) {
            return Store.initialize(HttpStatus.OK,
                    String.format("No students found in Stander = %s and Section = %c", stander, section ));
        }

        repo.deleteAllByStanderAndSection(stander, section);

        return repo.findAllByStanderAndSection(stander, section).isEmpty()?
                Store.initialize(HttpStatus.OK,
                        "All the Student in the Section = " + section +
                                " and Section = " + section + " has been removed successfully") :
                Store.initialize(HttpStatus.BAD_REQUEST,
                        "All the Student in the Section = " + section +
                                " and Section = " + section + " cannot be able to removed");
    }
}
