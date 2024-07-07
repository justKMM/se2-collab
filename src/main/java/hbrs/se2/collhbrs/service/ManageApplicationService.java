package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.dto.ApplicationDTO;
import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.repository.ApplicationRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
public class ManageApplicationService implements Serializable {

    private final transient ApplicationRepository repository;

    public ManageApplicationService(ApplicationRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void createApplication(ApplicationDTO applicationDTO, UserDTO userDTO) {

        //TODO
        //Application ApplicationEntity = EntityFactory.createApplication(  );

        // Abspeicherung des Entity in die DB
        //this.repository.save( );
    }

    public List<ApplicationDTO> readAllApplications() {
        return new ArrayList<>();
        //TODO repository.findAllApplications();
    }
}
