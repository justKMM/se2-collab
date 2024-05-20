package hbrs.se2.collhbrs.repository;

import hbrs.se2.collhbrs.entity.Unternehmen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnternehmenRepository extends JpaRepository<Unternehmen, Long> {

}
