package com.valkyrie.student_service.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("MARKS-SERVICE")
public interface MarksFeignController {

}
