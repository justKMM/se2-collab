package hbrs.se2.collhbrs.repository;

import hbrs.se2.collhbrs.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentByUser_UserID(Long userID);

    boolean existsByUser_UserID(Long userID);
}
