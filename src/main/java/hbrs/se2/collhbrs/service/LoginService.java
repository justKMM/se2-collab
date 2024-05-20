package hbrs.se2.collhbrs.service;

<<<<<<< src/main/java/hbrs/se2/collhbrs/service/LoginService.java
import hbrs.se2.collhbrs.entity.Benutzer;
import hbrs.se2.collhbrs.repository.BenutzerRepository;
import jakarta.transaction.Transactional;
=======
import hbrs.se2.collhbrs.entity.Benutzer;
import hbrs.se2.collhbrs.repository.BenutzerRepository;
>>>>>>> src/main/java/hbrs/se2/collhbrs/service/LoginService.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
<<<<<<< src/main/java/hbrs/se2/collhbrs/service/LoginService.java
    @Autowired
    private BenutzerRepository benutzerRepository;

    @Transactional
    protected Benutzer getBenutzerWithJPA(String username, String password) {
        Benutzer tmpBenutzer;
        tmpBenutzer = benutzerRepository.findBenutzerByUsernameAndPasswort(username, password);
        return tmpBenutzer;
    }


=======

    @Autowired
    private BenutzerRepository benutzerRepository;

    public Benutzer getBenutzer(String username, String passwort) {
        return benutzerRepository.findBenutzerByUsernameAndPasswort(username, passwort);
    }

>>>>>>> src/main/java/hbrs/se2/collhbrs/service/LoginService.java
}
