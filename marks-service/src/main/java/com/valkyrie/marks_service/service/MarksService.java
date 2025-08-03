package com.valkyrie.marks_service.service;

import com.valkyrie.marks_service.model.Marks;
import com.valkyrie.marks_service.model.MarksWrapper;
import com.valkyrie.marks_service.model.Store;
import com.valkyrie.marks_service.repository.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MarksService {
    private MarksRepository repo;
    @Autowired
    private void setRepo(MarksRepository repo) {this.repo = repo;}

    private MarksWrapper getMarks(Marks presentMarks) {

        return new MarksWrapper().setBengali(presentMarks.getBengali())
                .setBiology(presentMarks.getBiology()).setDrawing(presentMarks.getDrawing())
                .setComputer(presentMarks.getComputer()).setChemistry(presentMarks.getChemistry())
                .setEnglishI(presentMarks.getEnglishI()).setEnglishII(presentMarks.getEnglishII())
                .setGeography(presentMarks.getGeography()).setGsc(presentMarks.getGsc()).setGk(presentMarks.getGk())
                .setHindi(presentMarks.getHindi()).setMaths(presentMarks.getMaths()).setMsc(presentMarks.getMsc())
                .setPhysics(presentMarks.getPhysics()).setSst(presentMarks.getSst());
    }

    //save&update
    public Store<String> save(Marks marks) {
        boolean check = marks.getId() == null;

        if (check) {
            repo.save(marks.setId(UUID.randomUUID().toString()));
            return Store.initialize(HttpStatus.ACCEPTED, "Marks has been added successfully....");
        } else if (marks.toString().equals(repo.findById(marks.getId()).orElse(marks).toString())) {
            repo.save(marks);
            return Store.initialize(HttpStatus.ACCEPTED, "Marks has been updated successfully....");
        }

        return Store.initialize(HttpStatus.BAD_REQUEST, "No Marks has been updated/saved.....");
    }

    //find
    public Store<MarksWrapper> findMarksByStudentId(String studentId) {
        Marks presentMarks = repo.findByStudentId(studentId);

        if (presentMarks == null) {
            return Store.initialize(HttpStatus.BAD_REQUEST, null);
        }

        return Store.initialize(HttpStatus.OK, getMarks(presentMarks));
    }

    public Store<MarksWrapper> findMarksByMarksId(String id) {
        Marks presentMarks = repo.findByStudentId(id);

        if (presentMarks == null) {
            return Store.initialize(HttpStatus.BAD_REQUEST, null);
        }

        return Store.initialize(HttpStatus.OK, getMarks(presentMarks));
    }

    //delete
    @Transactional
    public Store<List<String>> removeMarksByStudentId(List<String> ids) {
        List<String> message = new ArrayList<>();

        for (String id : ids) {
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
