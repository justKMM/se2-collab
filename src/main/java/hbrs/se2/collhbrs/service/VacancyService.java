package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.repository.ApplicationRepository;
import hbrs.se2.collhbrs.repository.RequirementsRepository;
import hbrs.se2.collhbrs.repository.ResponsibilitiesRepository;
import hbrs.se2.collhbrs.repository.VacancyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VacancyService {

    private final VacancyRepository vacancyRepository;
    private final ResponsibilitiesRepository responsibilitiesRepository;
    private final RequirementsRepository requirementsRepository;
    private final ApplicationRepository applicationRepository;

    public VacancyService(VacancyRepository vacancyRepository,
                          ResponsibilitiesRepository responsibilitiesRepository,
                          RequirementsRepository requirementsRepository,
                          ApplicationRepository applicationRepository) {
        this.vacancyRepository = vacancyRepository;
        this.responsibilitiesRepository = responsibilitiesRepository;
        this.requirementsRepository = requirementsRepository;
        this.applicationRepository = applicationRepository;
    }

    public void saveVacancy(Vacancy vacancy) {
        this.vacancyRepository.save(vacancy);
    }

    public List<Vacancy> getVacanciesByBusinessId(Long businessId) {
        return vacancyRepository.findVacancByBusinessBusinessID(businessId);
    }

    public List<Vacancy> getAllVacancies() {
        return vacancyRepository.findAll();
    }

    @Transactional
    public void deleteVacancy(Long vacancyId) {
        responsibilitiesRepository.deleteByVacancyVacancyID(vacancyId);
        requirementsRepository.deleteByVacancyVacancyID(vacancyId);
        applicationRepository.deleteApplicationByVacancy_VacancyID(vacancyId);
        vacancyRepository.deleteByVacancyID(vacancyId);
    }

    public boolean isEmpty() {
        return vacancyRepository.count() == 0;
    }
}
