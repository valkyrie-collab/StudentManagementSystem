package com.valkyrie.authentication_service.feign;

import com.valkyrie.authentication_service.model.Teacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("TEACHER-SERVICE")
public interface TeacherFeignController {
    @PostMapping("/teacher/save-teacher")
    ResponseEntity<String> save(@RequestBody Teacher teacher);
}
