package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.entity.Benutzer;
import hbrs.se2.collhbrs.entity.Profil;
import hbrs.se2.collhbrs.repository.ProfilRepository;
import hbrs.se2.collhbrs.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private ProfilRepository profilRepository;

    @Transactional
    public void register(Benutzer user) {
        registerRepository.save(user);
    }

    @Transactional
    public void saveProfil(Profil profil) {
        profilRepository.save(profil);
    }
}
