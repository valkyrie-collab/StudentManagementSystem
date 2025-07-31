package com.valkyrie.teacher_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.valkyrie.teacher_service.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String>{

    @Query("SELECT t FROM Teacher t WHERE t.firstName = :firstName AND t.secondName = :secondName")
    List<Teacher> findAllByName(@Param("firstName") String firstName,
                                @Param("secondName") String secondName);


    List<Teacher> findAllByFirstName(String firstName);

    List<Teacher> findAllBySecondName(String secondName);

    List<Teacher> findAllBySubject(String subject);

    void deleteAllBySubject(String subject);
}
