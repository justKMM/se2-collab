package hbrs.se2.collhbrs.repository;

import hbrs.se2.collhbrs.model.entity.Responsibilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsibilitiesRepository extends JpaRepository<Responsibilities, Long> {

}
