package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.entity.Benutzer;
import hbrs.se2.collhbrs.entity.Profil;
import hbrs.se2.collhbrs.repository.ProfilRepository;
import hbrs.se2.collhbrs.repository.BenutzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private BenutzerRepository benutzerRepository;

    @Autowired
    private ProfilRepository profilRepository;

    public boolean completeRegistration(Benutzer benutzer) {
        String username = benutzer.getUsername();
        String password = benutzer.getPasswort();
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        saveBenutzer(benutzer);
        return true;
    }

    public void saveBenutzer(Benutzer benutzer) {
        benutzerRepository.save(benutzer);
    }

    public void saveProfil(Profil profil) {
        profilRepository.save(profil);
    }
}
