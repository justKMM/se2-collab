package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VacancyService {

    @Autowired
    private VacancyRepository repository;

    public void saveVacancy(Vacancy vacancy) {
        this.repository.save(vacancy);
    }
}
