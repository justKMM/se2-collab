package hbrs.se2.collhbrs.repository;


import hbrs.se2.collhbrs.model.entity.traits.DegreeProgramm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeProgrammRepository extends JpaRepository<DegreeProgramm, Long> {
}
