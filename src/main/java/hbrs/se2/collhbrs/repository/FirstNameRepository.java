package hbrs.se2.collhbrs.repository;

import hbrs.se2.collhbrs.entity.FirstName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstNameRepository extends JpaRepository<FirstName, Long> {
}
