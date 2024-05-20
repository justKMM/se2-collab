package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.entity.*;
import hbrs.se2.collhbrs.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {

    @Autowired
    private BenutzerRepository benutzerRepository;

    @Autowired
    private ProfilRepository profilRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private VornameRepository vornameRepository;

    @Autowired
    private UnternehmenRepository unternehmenRepository;

    @Transactional
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

    @Transactional
    public void saveBenutzer(Benutzer benutzer) {
        benutzerRepository.save(benutzer);
    }

    @Transactional
    public void saveProfil(Profil profil) {
        profilRepository.save(profil);
    }

    @Transactional
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Transactional
    public void saveVorname(Vorname vorname) {
        vornameRepository.save(vorname);
    }

    @Transactional
    public void saveUnternehmen(Unternehmen unternehmen) {
        unternehmenRepository.save(unternehmen);
    }
}
