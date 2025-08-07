package com.valkyrie.student_service.service;

import com.valkyrie.student_service.feign.MarksFeignController;
import com.valkyrie.student_service.feign.TeacherFeignController;
import com.valkyrie.student_service.model.*;
import com.valkyrie.student_service.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class StudentService {
    String[] modifiedName;

//    private static final Student defaultStudent = new Student().setId("null").setFirstName("null")
//            .setSecondName("null").setFatherFirstName("null").setFatherSecondName("null").setImage(null)
//            .setEnrolment(null).setFatherSecondName("null").setMotherFirstName("null").setMotherSecondName("null")
//            .setPassOut(null).setDob(null).setRole((byte)-1).setContact(-1L).setSection('N').setStander("null")
//            .setClassTeacherId("null").setClassTeacherName("null");
//    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder(12);

    private static final List<StudentWrapper> studentWrappers = new LinkedList<>();

    private StudentRepository repo;
    @Autowired
    private void setRepo(StudentRepository repo) {this.repo = repo;}

    private MarksFeignController marksFeign;
    @Autowired
    private void setMarksFeign(MarksFeignController marksFeign) {this.marksFeign = marksFeign;}

    private TeacherFeignController teacherFeign;
    @Autowired
    private void setTeacherFeign(TeacherFeignController teacherFeign) {this.teacherFeign = teacherFeign;}

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

    private StudentWrapper getWrapper(Student student) {
        ResponseEntity<MarksWrapper> marks = marksFeign.findByStudentId(student.getId());

        return new StudentWrapper().setBloodGroup(student.getBloodGroup())
                .setContact(student.getContact()).setDob(student.getDob())
                .setEnrolment(student.getEnrolment()).setId(student.getId())
                .setClassTeacherId(student.getClassTeacherId())
                .setFatherFirstName(student.getFatherFirstName())
                .setFatherSecondName(student.getFatherSecondName())
                .setMotherFirstName(student.getMotherFirstName())
                .setMotherSecondName(student.getMotherSecondName())
                .setFirstName(student.getFirstName())
                .setSecondName(student.getSecondName()).setImage(student.getImage())
                .setMarks(
                        (marks.getStatusCode().equals(HttpStatusCode.valueOf(200))?
                                marks.getBody() : null
                        )
                )
                .setPassOut(student.getPassOut()).setRole(student.getRole())
                .setSection(student.getSection()).setStander(student.getStander());

    }

    public Store<List<String>> save(List<Student> students) {
        List<String> message = new ArrayList<>(students.size());

        for (Student student : students) {
            boolean check = student.getId() == null;
            String[] modifiedName = getFirstSecondName(student.getFirstName(), student.getSecondName());
            String[] modifiedFatherName = getFirstSecondName(
                    student.getFatherFirstName(), student.getFatherSecondName()
            );
            String[] modifiedMotherName = getFirstSecondName(
                    student.getMotherFirstName(), student.getMotherSecondName()
            );
            ResponseEntity<TeacherWrapper> teacher =
                    teacherFeign.findTeacherById(student.getClassTeacherId());

            if (modifiedName[0] == null ||
                    (!teacher.getStatusCode().equals(
                                    HttpStatusCode.valueOf(200)
                            ) && teacher.getBody() == null
                    ) ||
                    modifiedFatherName[0] == null || modifiedMotherName[0] == null) {
                return Store.initialize(HttpStatus.BAD_REQUEST, List.of("There is problem with name"));
            }

            student = student.setFirstName(modifiedName[0])
                    .setSecondName(modifiedName[1])
                    .setFatherFirstName(modifiedFatherName[0])
                    .setFatherSecondName(modifiedFatherName[1])
                    .setMotherFirstName(modifiedMotherName[0])
                    .setMotherSecondName(modifiedMotherName[1]);

            if (check) {
                String uuid = UUID.randomUUID().toString();
                student = student.setId(uuid);
                repo.save(student);
                message.add("The Student With ID = " + uuid + " has been added successfully...");
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
    public Store<StudentWrapper> findStudentById(String id) {
//        id = new String(Base64.getDecoder().decode(id));
        Student student = repo.findById(id).orElse(null);
        StudentWrapper studentWrapper = null;

        if (student != null) {
            studentWrapper = getWrapper(student);
        }

        return student == null? Store.initialize(HttpStatus.BAD_REQUEST, studentWrapper) :
                Store.initialize(HttpStatus.OK, studentWrapper);
    }

    @Transactional
    public Store<List<StudentWrapper>> findStudentsByClassTeacherId(String teacherId) {
//        teacherId = new String(Base64.getDecoder().decode(teacherId));
        List<Student> students = repo.findAllByClassTeacherId(teacherId);

        if (!students.isEmpty()) {

            for (Student student : students) {
                studentWrappers.add(getWrapper(student));
            }

        }

        return !studentWrappers.isEmpty()? Store.initialize(HttpStatus.OK, studentWrappers) :
                Store.initialize(HttpStatus.BAD_REQUEST, studentWrappers);
    }

//    @Transactional
//    public Store<List<StudentWrapper>> findStudentsByClassTeacherName(String classTeacherName) {
//        List<Student> students = repo.findAllByClassTeacherName(classTeacherName);
//
//        if (!students.isEmpty()) {
//
//            for (Student student : students) {
//                studentWrappers.add(getWrapper(student));
//            }
//
//        }
//
//        return !students.isEmpty()? Store.initialize(HttpStatus.OK, studentWrappers) :
//                Store.initialize(HttpStatus.BAD_REQUEST, studentWrappers);
//    }

    @Transactional
    public Store<List<StudentWrapper>> findStudentByName(String firstName, String secondName) {
        modifiedName = getFirstSecondName(firstName, secondName);
        List<Student> students = new ArrayList<>();

        if (firstName != null && secondName != null) {
            students = repo.findAllByName(modifiedName[0], modifiedName[1]);
        } else if (firstName != null) {
            students = repo.findAllByFirstName(modifiedName[0]);
        } else if (secondName != null) {
            students = repo.findAllBySecondName(modifiedName[1]);
        } else {
            return Store.initialize(HttpStatus.BAD_REQUEST, studentWrappers);
        }

        if (!students.isEmpty()) {

            for (Student student : students) {
                studentWrappers.add(getWrapper(student));
            }

        }

        return !students.isEmpty()? Store.initialize(HttpStatus.OK, studentWrappers) :
                Store.initialize(HttpStatus.BAD_REQUEST, studentWrappers);
    }

    @Transactional
    public Store<List<StudentWrapper>> findStudentByFatherName(String fatherFirstName,
                                                        String fatherSecondName) {
        modifiedName = getFirstSecondName(fatherFirstName, fatherSecondName);
        List<Student> students = new ArrayList<>();

        if (fatherFirstName != null && fatherSecondName != null) {
            students = repo.findAllByName(modifiedName[0], modifiedName[1]);
        } else if (fatherFirstName != null) {
            students = repo.findAllByFirstName(modifiedName[0]);
        } else if (fatherSecondName != null) {
            students = repo.findAllBySecondName(modifiedName[1]);
        } else {
            return Store.initialize(HttpStatus.BAD_REQUEST, studentWrappers);
        }

        if (!students.isEmpty()) {

            for (Student student : students) {
                studentWrappers.add(getWrapper(student));
            }

        }

        return !students.isEmpty()? Store.initialize(HttpStatus.OK, studentWrappers) :
                Store.initialize(HttpStatus.BAD_REQUEST, studentWrappers);
    }

    @Transactional
    public Store<List<StudentWrapper>> findStudentByMotherName(String motherFirstName,
                                                        String motherSecondName) {
        modifiedName = getFirstSecondName(motherFirstName, motherSecondName);
        List<Student> students = new ArrayList<>();

        if (motherFirstName != null && motherSecondName != null) {
            students = repo.findAllByMotherName(modifiedName[0], modifiedName[1]);
        } else if (motherFirstName != null) {
            students = repo.findAllByMotherFirstName(modifiedName[0]);
        } else if (motherSecondName != null) {
            students = repo.findAllByMotherSecondName(modifiedName[1]);
        } else {
            return Store.initialize(HttpStatus.BAD_REQUEST, studentWrappers);
        }

        if (!students.isEmpty()) {

            for (Student student : students) {
                studentWrappers.add(getWrapper(student));
            }

        }

        return !students.isEmpty()? Store.initialize(HttpStatus.OK, studentWrappers) :
                Store.initialize(HttpStatus.BAD_REQUEST, studentWrappers);
    }

    @Transactional
    public Store<List<StudentWrapper>> findStudentByStanderAndSection(String stander, char section) {
        List<Student> students = new ArrayList<>();

        if (stander != null && section < 'E') {
            students = repo.findAllByStanderAndSection(stander, section);
        } else if (stander != null) {
            students = repo.findAllByStander(stander);
        } else if (section < 'E') {
            students = repo.findAllBySection(section);
        } else {
            return Store.initialize(HttpStatus.BAD_REQUEST, studentWrappers);
        }

        if (!students.isEmpty()) {

            for (Student student : students) {
                studentWrappers.add(getWrapper(student));
            }

        }

        return !students.isEmpty()? Store.initialize(HttpStatus.OK, studentWrappers) :
                Store.initialize(HttpStatus.BAD_REQUEST, studentWrappers);
    }

    //DeleteStudent
    public Store<String> deleteStudentById(String id) {
        id = new String(Base64.getDecoder().decode(id));

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
