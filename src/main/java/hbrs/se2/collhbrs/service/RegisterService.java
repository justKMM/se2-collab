package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.entity.Benutzer;
import hbrs.se2.collhbrs.entity.Profil;
import hbrs.se2.collhbrs.entity.Student;
import hbrs.se2.collhbrs.entity.Vorname;
import hbrs.se2.collhbrs.repository.ProfilRepository;
import hbrs.se2.collhbrs.repository.BenutzerRepository;
import hbrs.se2.collhbrs.repository.StudentRepository;
import hbrs.se2.collhbrs.repository.VornameRepository;
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
}
