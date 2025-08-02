package com.valkyrie.student_service.repository;

import com.valkyrie.student_service.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    List<Student> findAllByFirstName(String firstName);

    List<Student> findAllBySecondName(String secondName);

    @Query("SELECT t FROM Student t WHERE t.firstName = :firstName AND t.secondName = :secondName")
    List<Student> findAllByName(@Param("firstName") String firstName,
                                @Param("secondName") String secondName);

    List<Student> findAllByFatherFirstName(String fatherFirstName);

    List<Student> findAllByFatherSecondName(String fatherSecondName);

    @Query("SELECT t FROM Student t WHERE t.fatherFirstName = :fatherFirstName AND t.fatherSecondName = :fatherSecondName")
    List<Student> findAllByFatherName(@Param("fatherFirstName") String fatherFirstName,
                                      @Param("fatherSecondName") String fatherSecondName);

    List<Student> findAllByMotherFirstName(String motherFirstName);

    List<Student> findAllByMotherSecondName(String motherSecondName);

    @Query("SELECT t FROM Student t WHERE t.motherFirstName = :motherFirstName AND t.motherSecondName = :motherSecondName")
    List<Student> findAllByMotherName(@Param("motherFirstName") String motherFirstName,
                                      @Param("motherSecondName") String motherSecondName);

    List<Student> findAllByStander(String stander);

    List<Student> findAllBySection(char section);

    @Query("SELECT t FROM Student t WHERE t.stander = :stander AND t.section = :section")
    List<Student> findAllByStanderAndSection(@Param("stander") String stander,
                                             @Param("section") char section);

    @Modifying
    @Query("DELETE FROM Student s WHERE s.stander = :stander AND s.section = :section")
    void deleteAllByStanderAndSection(@Param("stander") String stander,
                                      @Param("section") char section);
}
