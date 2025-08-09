package com.valkyrie.authentication_service.feign;

import com.valkyrie.authentication_service.model.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("STUDENT-SERVICE")
public interface StudentFeignController {
    @PostMapping("/student/save-student")
    ResponseEntity<List<String>> save(@RequestBody Student student);
}
