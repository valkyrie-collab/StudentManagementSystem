package com.valkyrie.marks_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("STUDENT-SERVICE")
public interface StudentFeignController {

    @GetMapping("/student/check-student-present")
    ResponseEntity<Boolean> checkStudentPresent(@RequestParam String id);
}
