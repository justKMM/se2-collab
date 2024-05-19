package hbrs.se2.collhbrs.repository;

import hbrs.se2.collhbrs.dto.BenutzerDTO;
import hbrs.se2.collhbrs.entity.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenutzerRepository extends JpaRepository<Benutzer, Long> {
    //search for Benutzer based on username and password
    BenutzerDTO findBenutzerByUsernameAndPasswort (String username , String password);
}
