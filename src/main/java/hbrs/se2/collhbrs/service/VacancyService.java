package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.repository.BusinessRepository;
import hbrs.se2.collhbrs.repository.RequirementsRepository;
import hbrs.se2.collhbrs.repository.ResponsibilitiesRepository;
import hbrs.se2.collhbrs.repository.VacancyRepository;
import hbrs.se2.collhbrs.util.EntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VacancyService {

    private final VacancyRepository vacancyRepository;
    private final ResponsibilitiesRepository responsibilitiesRepository;
    private final RequirementsRepository requirementsRepository;

    @Autowired
    private EntityFactory entityFactory;
    @Autowired
    private BusinessRepository businessRepository;

    public VacancyService(VacancyRepository vacancyRepository, ResponsibilitiesRepository responsibilitiesRepository, RequirementsRepository requirementsRepository) {
        this.vacancyRepository = vacancyRepository;
        this.responsibilitiesRepository = responsibilitiesRepository;
        this.requirementsRepository = requirementsRepository;
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

    @Transactional
    public void deleteVacancy(Long vacancyId) {
        responsibilitiesRepository.deleteByVacancyVacancyID(vacancyId);
        requirementsRepository.deleteByVacancyVacancyID(vacancyId);
        vacancyRepository.deleteByVacancyID(vacancyId);
    }

    public List<Vacancy> getVacanciesByTitle(String title) {
        return vacancyRepository.findVacanciesByTitle(title);
    }

    public boolean isEmpty() {
        return vacancyRepository.count() == 0;
    }
}
