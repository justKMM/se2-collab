package hbrs.se2.collhbrs.repository;

import hbrs.se2.collhbrs.entity.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnternehmenRepository extends JpaRepository<Benutzer, Long> {

}
