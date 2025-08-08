package com.valkyrie.marks_service.service;

import com.valkyrie.marks_service.feign.StudentFeignController;
import com.valkyrie.marks_service.model.Marks;
import com.valkyrie.marks_service.model.MarksWrapper;
import com.valkyrie.marks_service.model.Store;
import com.valkyrie.marks_service.repository.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class MarksService {
    private MarksRepository repo;
    @Autowired
    private void setRepo(MarksRepository repo) {this.repo = repo;}

    private StudentFeignController studentFeign;
    @Autowired
    private void setStudentFeign(StudentFeignController studentFeign) {this.studentFeign = studentFeign;}

    private MarksWrapper getMarks(Marks presentMarks) {

        return new MarksWrapper().setBengali(presentMarks.getBengali())
                .setHistoryAndCivics(presentMarks.getHistoryAndCivics())
                .setBiology(presentMarks.getBiology()).setDrawing(presentMarks.getDrawing())
                .setComputer(presentMarks.getComputer()).setChemistry(presentMarks.getChemistry())
                .setEnglishI(presentMarks.getEnglishI()).setEnglishII(presentMarks.getEnglishII())
                .setGeography(presentMarks.getGeography()).setGsc(presentMarks.getGsc()).setGk(presentMarks.getGk())
                .setHindi(presentMarks.getHindi()).setMaths(presentMarks.getMaths()).setMsc(presentMarks.getMsc())
                .setPhysics(presentMarks.getPhysics()).setSst(presentMarks.getSst()).setTerm(presentMarks.getTerm());
    }

    //save&update
    public Store<String> save(Marks marks) {
        boolean check = marks.getId() == null;
        boolean checkTerm = marks.getTerm() == null;
        ResponseEntity<Boolean> checkStudent = studentFeign.checkStudentPresent(marks.getStudentId());

        if (checkStudent == null || checkStudent.getBody() == null) {
            return Store.initialize(HttpStatus.BAD_REQUEST, "Marks not Saved");
        }

//        studentId = new String(Base64.getDecoder().decode(studentId));
        LocalDate currentDate = LocalDate.now();
        LocalDate january = LocalDate.of(currentDate.getYear(), 1, 1);
        LocalDate April = LocalDate.of(currentDate.getYear(), 4, 1);
        LocalDate August = LocalDate.of(currentDate.getYear(), 8, 1);
        LocalDate December = LocalDate.of(currentDate.getYear(), 12, 31);

        if (check && checkTerm && checkStudent.getBody()) {
            String term = null;

            if (currentDate.isAfter(August)) {
                term = "3rd-Term";
            } else if (currentDate.isAfter(April)) {
                term = "2nd-Term";
            } else if (currentDate.isAfter(january)) {
                term = "1st-Term";
            } else {
                term = "no valid";
            }

            repo.save(marks.setId(UUID.randomUUID().toString())
                    .setStudentId(marks.getStudentId()).setTerm(term));
            return Store.initialize(HttpStatus.ACCEPTED, "Marks has been added successfully....");
        } else if (marks.toString().equals(repo.findById(marks.getId()).orElse(marks).toString())) {
            repo.save(marks);
            return Store.initialize(HttpStatus.ACCEPTED, "Marks has been updated successfully....");
        }

        return Store.initialize(HttpStatus.BAD_REQUEST, "No Marks has been updated/saved.....");
    }

    //find
    public Store<List<MarksWrapper>> findMarksByStudentId(String studentId) {
//        studentId = new String(Base64.getDecoder().decode(studentId));
        List<Marks> presentMarks = repo.findByStudentId(studentId);
        List<MarksWrapper> wrappers = new ArrayList<>();

        if (presentMarks == null || presentMarks.isEmpty()) {
            return Store.initialize(HttpStatus.BAD_REQUEST, wrappers);
        }

        for (Marks marks : presentMarks) {
            wrappers.add(getMarks(marks));
        }

        return Store.initialize(HttpStatus.OK, wrappers);
    }

    public Store<MarksWrapper> findMarksByMarksId(String id) {
//        id = new String(Base64.getDecoder().decode(id));
        Marks presentMarks = repo.findById(id).orElse(null);

        if (presentMarks == null) {
            return Store.initialize(HttpStatus.BAD_REQUEST, null);
        }

        return Store.initialize(HttpStatus.OK, getMarks(presentMarks));
    }

    //delete
    public Store<String> removeMarksById(String id) {

        if (repo.findById(id).orElse(null) == null) {
            return Store.initialize(HttpStatus.OK,
                    "The Marks With This ID = " + id + "Has Already been removed"
            );
        }

        repo.deleteById(id);

        return repo.findById(id).orElse(null) == null?
                Store.initialize(HttpStatus.OK,
                        "The Marks With the ID = " + id + "Has been removed successfully"
                ) : Store.initialize(HttpStatus.BAD_REQUEST, "Deletion is Unsuccessful");
    }

    @Transactional
    public Store<List<String>> removeMarksByStudentId(List<String> ids) {
        List<String> message = new ArrayList<>();

        for (String id : ids) {
//            id = new String(Base64.getDecoder().decode(id));
            if (repo.findByStudentId(id) == null) {
                message.add("No Marks was there for student with ID = " + id +
                        " either it is already been deleted or check the ID......");
            } else {
                repo.deleteByStudentId(id);
                message.add("The Marks Related to the Student ID = " + id + " has been deleted successfully....");
            }
        }

        return Store.initialize(HttpStatus.OK, message);
    }
}
