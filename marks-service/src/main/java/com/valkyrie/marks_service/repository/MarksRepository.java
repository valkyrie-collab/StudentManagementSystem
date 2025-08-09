package com.valkyrie.marks_service.repository;

import com.valkyrie.marks_service.model.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarksRepository extends JpaRepository<Marks, String> {

    List<Marks> findByStudentId(String studentId);

//    void deleteByStudentId(String id);

    void deleteAllByStudentId(String studentId);
}
