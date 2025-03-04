package hbrs.se2.collhbrs.repository;

import hbrs.se2.collhbrs.model.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
    Business findBusinessByUserUserID(Long userID);

    boolean existsByUserUserID(Long userID);
}
