package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyService {

    @Autowired
    private VacancyRepository vacancyRepository;

    public void saveVacancy(Vacancy vacancy) {
        this.vacancyRepository.save(vacancy);
    }

    public List<Vacancy> getVacanciesByBusinessId(Long businessId) {
        return vacancyRepository.findVacanciesByBusiness_BusinessID(businessId);
    }

    public Vacancy getVacancyByBusinessId(Long businessId) {
        return vacancyRepository.findVacancyByBusiness_BusinessID(businessId);
    }
}
