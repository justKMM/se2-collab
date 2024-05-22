package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.entity.Benutzer;
import hbrs.se2.collhbrs.repository.BenutzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {

    @Autowired
    private BenutzerRepository benutzerRepository;

    @Transactional
    public Benutzer getBenutzer(String username, String passwort) {
        return benutzerRepository.findBenutzerByUsernameAndPasswort(username, passwort);
    }
}
