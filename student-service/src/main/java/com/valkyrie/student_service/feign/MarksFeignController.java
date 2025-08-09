package com.valkyrie.student_service.feign;

import com.valkyrie.student_service.model.MarksWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("MARKS-SERVICE")
public interface MarksFeignController {

    @GetMapping("/marks/find-marks-by-student-id")
    ResponseEntity<List<MarksWrapper>> findByStudentId(@RequestParam String studentId);

    @DeleteMapping("/marks/remove-marks-by-student-id")
    ResponseEntity<List<String>> removeByStudentId(@RequestBody List<String> ids);
}
