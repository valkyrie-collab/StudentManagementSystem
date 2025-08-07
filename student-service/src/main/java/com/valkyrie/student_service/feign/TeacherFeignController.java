package com.valkyrie.student_service.feign;

import com.valkyrie.student_service.model.TeacherWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("TEACHER-SERVICE")
public interface TeacherFeignController {

    @GetMapping("/teacher/find-teacher-by-id")
    ResponseEntity<TeacherWrapper> findTeacherById(@RequestParam String id);

    @GetMapping("/teacher/check-for-teacher")
    ResponseEntity<String> checkForTeacher(@RequestParam String id);

    @GetMapping("/teacher/find-teacher-by-class")
    ResponseEntity<String> findTeacherId(@RequestParam("classTeacher") String classTeacher);
}
