package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.repository.VacancyRepository;
import hbrs.se2.collhbrs.util.EntityFactory;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class VacancyService {

    private final VacancyRepository vacancyRepository;
    private EntityFactory entityFactory;

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

    public void createVacancy(Business business,String employmenttype, String title, String description, String location, Date datePublish){

        Vacancy vacancy = entityFactory.createVacancy(employmenttype, title, location ,description, business ,datePublish);
        vacancyRepository.save(vacancy);
    }

    public List<Vacancy> getAllVacancies() {
        return vacancyRepository.findAll();
    }

    public List<Vacancy> getVacanciesByEmploymentType(String employmenttype){
        return vacancyRepository.findVacanciesByEmploymentType(employmenttype);
    }

    public List<Vacancy> getVacanciesByTitle(String title){
        return vacancyRepository.findVacanciesByTitle(title);
    }

    public boolean isEmpty(){
        return vacancyRepository.count() == 0;
    }
}
