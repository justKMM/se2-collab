package hbrs.se2.collhbrs.repository;

import hbrs.se2.collhbrs.model.entity.traits.Responsibilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponsibilitiesRepository extends JpaRepository<Responsibilities, Long> {
    List<Responsibilities> findResponsibilitiesByVacancyVacancyID(Long vacancyId);
    void deleteByVacancy_VacancyID(Long vacancyId);
}
