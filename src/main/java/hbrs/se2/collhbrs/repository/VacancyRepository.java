package hbrs.se2.collhbrs.repository;

import hbrs.se2.collhbrs.model.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    List<Vacancy> findVacanciesByBusiness_BusinessID(Long businessId);
    Vacancy findVacancyByBusiness_BusinessID(Long businessId);
}
