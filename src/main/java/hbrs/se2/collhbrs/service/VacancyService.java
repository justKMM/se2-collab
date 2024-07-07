package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.repository.*;
import hbrs.se2.collhbrs.util.EntityFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VacancyService {

    private final VacancyRepository vacancyRepository;
    private final ResponsibilitiesRepository responsibilitiesRepository;
    private final RequirementsRepository requirementsRepository;
    private final ApplicationRepository applicationRepository;
    private final EntityFactory entityFactory;
    private final BusinessRepository businessRepository;

    public VacancyService(VacancyRepository vacancyRepository,
                          ResponsibilitiesRepository responsibilitiesRepository,
                          RequirementsRepository requirementsRepository,
                          EntityFactory entityFactory,
                          BusinessRepository businessRepository,
                          ApplicationRepository applicationRepository) {
        this.vacancyRepository = vacancyRepository;
        this.responsibilitiesRepository = responsibilitiesRepository;
        this.requirementsRepository = requirementsRepository;
        this.entityFactory = entityFactory;
        this.businessRepository = businessRepository;
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

    public List<Vacancy> getVacanciesByEmploymentType(String employmenttype) {
        return vacancyRepository.findVacanciesByEmploymentType(employmenttype);
    }

    public Vacancy getVacancyByBusinessID(Long businessId) {
        return vacancyRepository.findVacancyByBusiness_BusinessID(businessId);
    }

    @Transactional
    public void deleteVacancy(Long vacancyId) {
        responsibilitiesRepository.deleteByVacancyVacancyID(vacancyId);
        requirementsRepository.deleteByVacancyVacancyID(vacancyId);
        applicationRepository.deleteApplicationByVacancy_VacancyID(vacancyId);
        vacancyRepository.deleteByVacancyID(vacancyId);
    }

    public List<Vacancy> getVacanciesByTitle(String title) {
        return vacancyRepository.findVacanciesByTitle(title);
    }

    public boolean isEmpty() {
        return vacancyRepository.count() == 0;
    }
}
