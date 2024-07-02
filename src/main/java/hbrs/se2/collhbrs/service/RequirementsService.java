package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.Requirements;
import hbrs.se2.collhbrs.repository.RequirementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequirementsService {

    @Autowired
    RequirementsRepository requirementsRepository;

    public void saveRequirements(Requirements requirements) {
        requirementsRepository.save(requirements);
    }

    public List<Requirements> getRequirementsByVacancyId(Long vacancyId) {
        return requirementsRepository.getRequirementsByVacancyVacancyID(vacancyId);
    }
}
