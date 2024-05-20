package hbrs.se2.collhbrs.repository;

import hbrs.se2.collhbrs.entity.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenutzerRepository extends JpaRepository<Benutzer, Long> {

    Benutzer findBenutzerByUsernameAndPasswort(String username, String passwort);
}
