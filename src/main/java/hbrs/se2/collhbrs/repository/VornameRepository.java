package hbrs.se2.collhbrs.repository;

import hbrs.se2.collhbrs.entity.Vorname;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VornameRepository extends JpaRepository<Vorname, Long> { }
