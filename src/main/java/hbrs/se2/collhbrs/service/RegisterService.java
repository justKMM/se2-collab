package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.entity.Benutzer;
import hbrs.se2.collhbrs.entity.Profil;
import hbrs.se2.collhbrs.repository.ProfilRepository;
import hbrs.se2.collhbrs.repository.BenutzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {

    @Autowired
    private BenutzerRepository benutzerRepository;

    @Autowired
    private ProfilRepository profilRepository;


    @Transactional
    public boolean completeRegistration(Benutzer benutzer) {
        // Registration control
        String username = benutzer.getUsername();
        String password = benutzer.getPasswort();
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            // Check if username contains special character(s)
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            // Check if username contains special character(s)
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }

        saveBenutzer(benutzer);
        saveProfil(benutzer.getProfil());
        return true;
    }

    @Transactional
    public void saveBenutzer(Benutzer benutzer) {
        benutzerRepository.save(benutzer);
    }

    @Transactional
    public void saveProfil(Profil profil) {
        profilRepository.save(profil);
    }
}
