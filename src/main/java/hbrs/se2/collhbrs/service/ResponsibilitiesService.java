package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.Responsibilities;
import hbrs.se2.collhbrs.repository.ResponsibilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsibilitiesService {

    @Autowired
    ResponsibilitiesRepository responsibilitiesRepository;

    public void saveResponsibilities(Responsibilities responsibilities) {
        responsibilitiesRepository.save(responsibilities);
    }

    public List<Responsibilities> getResponsibilitiesByVacancyId(Long vacancyId) {
        return responsibilitiesRepository.findResponsibilitiesByVacancyVacancyID(vacancyId);
    }
}
