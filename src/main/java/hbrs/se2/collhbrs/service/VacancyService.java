package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.repository.VacancyRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class VacancyService implements Serializable {

    private final VacancyRepository vacancyRepository;

    public VacancyService(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    public void saveVacancy(Vacancy vacancy) {
        this.vacancyRepository.save(vacancy);
    }

    public List<Vacancy> getVacanciesByBusinessId(Long businessId) {
        return vacancyRepository.findVacancByBusinessBusinessID(businessId);
    }

    public Vacancy getVacancyByBusinessId(Long businessId) {
        return vacancyRepository.findVacancyByBusinessBusinessID(businessId);
    }
}
