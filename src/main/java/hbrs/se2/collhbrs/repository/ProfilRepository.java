package hbrs.se2.collhbrs.repository;

import hbrs.se2.collhbrs.entity.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilRepository extends JpaRepository<Profil, Long> { }
