package hbrs.se2.collhbrs.repository;

import hbrs.se2.collhbrs.model.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    Vacancy findVacancyByBusiness_BusinessID(Long businessID);
    List<Vacancy> findVacancByBusinessBusinessID(Long businessId);
    List<Vacancy> findVacanciesByEmploymentType(String employmentType);
    List<Vacancy> findVacanciesByTitle(String title);
    void deleteByVacancyID(Long vacancyId);

}
