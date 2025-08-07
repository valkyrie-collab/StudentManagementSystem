package com.valkyrie.marks_service.controller;

import com.valkyrie.marks_service.model.Marks;
import com.valkyrie.marks_service.model.MarksWrapper;
import com.valkyrie.marks_service.model.Store;
import com.valkyrie.marks_service.service.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marks")
public class MarksController {
    private MarksService service;
    @Autowired
    private void setService(MarksService service) {this.service = service;}

    @PostMapping("/save-marks")
    public ResponseEntity<String> save(@RequestBody Marks marks) {
        Store<String> store = service.save(marks);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @PostMapping("/update-marks")
    public ResponseEntity<String> update(@RequestBody Marks marks) {return save(marks);}

    @GetMapping("/find-marks-by-student-id")
    public ResponseEntity<MarksWrapper> findByStudentId(@RequestParam String studentId) {
        Store<MarksWrapper> store = service.findMarksByStudentId(studentId);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-marks-by-marks-id")
    public ResponseEntity<MarksWrapper> findByMarksId(@RequestParam String id) {
        Store<MarksWrapper> store = service.findMarksByMarksId(id);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @DeleteMapping("/remove-marks-by-student-id")
    public ResponseEntity<List<String>> removeByStudentId(@RequestBody List<String> ids) {
        Store<List<String>> store = service.removeMarksByStudentId(ids);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }
}
