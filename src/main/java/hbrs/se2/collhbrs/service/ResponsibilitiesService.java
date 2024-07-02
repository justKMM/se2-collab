package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.Responsibilities;
import hbrs.se2.collhbrs.repository.ResponsibilitiesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsibilitiesService {

    final
    ResponsibilitiesRepository responsibilitiesRepository;

    public ResponsibilitiesService(ResponsibilitiesRepository responsibilitiesRepository) {
        this.responsibilitiesRepository = responsibilitiesRepository;
    }

    public void saveResponsibilities(Responsibilities responsibilities) {
        responsibilitiesRepository.save(responsibilities);
    }

    public List<Responsibilities> getResponsibilitiesByVacancyId(Long vacancyId) {
        return responsibilitiesRepository.findResponsibilitiesByVacancyVacancyID(vacancyId);
    }
}
