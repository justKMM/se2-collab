package hbrs.se2.collhbrs.repository;

import hbrs.se2.collhbrs.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentByUserUserID(Long userID);

    boolean existsByUserUserID(Long userID);
}
