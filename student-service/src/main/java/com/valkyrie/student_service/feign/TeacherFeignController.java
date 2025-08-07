package com.valkyrie.student_service.feign;

import com.valkyrie.student_service.model.TeacherWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("TEACHER-SERVICE")
public interface TeacherFeignController {

    @GetMapping("/find-teacher-by-id")
    ResponseEntity<TeacherWrapper> findTeacherById(@RequestParam String id);
}
