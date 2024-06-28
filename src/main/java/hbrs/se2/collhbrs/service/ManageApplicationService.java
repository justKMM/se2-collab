package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.dto.ApplicationDTO;
import hbrs.se2.collhbrs.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import hbrs.se2.collhbrs.model.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class ManageApplicationService {

    @Autowired
    private ApplicationRepository repository;

    @Transactional
    public void createApplication(ApplicationDTO applicationDTO , UserDTO userDTO ) {

        //TODO
        //Application ApplicationEntity = EntityFactory.createApplication(  );

        // Abspeicherung des Entity in die DB
        //this.repository.save( );
    }

    public List<ApplicationDTO> readAllApplications() {
        return new ArrayList<ApplicationDTO>();
        //TODO repository.findAllApplications();
    }
}
