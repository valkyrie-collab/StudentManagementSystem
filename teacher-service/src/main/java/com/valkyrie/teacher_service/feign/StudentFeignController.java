package com.valkyrie.teacher_service.feign;

import com.valkyrie.teacher_service.model.StudentWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("STUDENT-SERVICE")
public interface StudentFeignController {

    @GetMapping("/student/find-students-by-teacher-id")
    ResponseEntity<List<StudentWrapper>> findByTeacherId(@RequestParam String teacherId, @RequestParam boolean doFeign);

    @GetMapping("/find-students-by-teacher-name")
    ResponseEntity<List<StudentWrapper>> findByTeacherName(@RequestParam String teacherName);
}
