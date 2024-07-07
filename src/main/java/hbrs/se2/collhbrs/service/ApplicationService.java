package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.dto.ApplicationDTO;
import hbrs.se2.collhbrs.model.entity.Application;
import hbrs.se2.collhbrs.repository.ApplicationRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationService implements Serializable {

    private final ApplicationRepository repository;

    public ApplicationService(ApplicationRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void saveApplication(Application application) {
        repository.save(application);
    }

    public List<ApplicationDTO> readAllApplications() {
        return new ArrayList<>();
        //TODO repository.findAllApplications();
    }
}
