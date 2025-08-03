package com.valkyrie.marks_service.repository;

import com.valkyrie.marks_service.model.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarksRepository extends JpaRepository<Marks, String> {

    Marks findByStudentId(String studentId);

    void deleteByStudentId(String id);
}
