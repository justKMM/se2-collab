package hbrs.se2.collhbrs.repository;

import hbrs.se2.collhbrs.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> { }
