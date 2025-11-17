package itst.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import itst.example.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

    @Query(value = "SELECT * FROM students WHERE LOWER(name) = LOWER(:name);", nativeQuery = true)
    List<Student> getStudentsByName(@Param("name") String name);
}