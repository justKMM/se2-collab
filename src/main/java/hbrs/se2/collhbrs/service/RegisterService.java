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
    public void saveBenutzer(Benutzer benutzer) {
        benutzerRepository.save(benutzer);
    }

    @Transactional
    public void saveProfil(Profil profil) {
        profilRepository.save(profil);
    }
}
