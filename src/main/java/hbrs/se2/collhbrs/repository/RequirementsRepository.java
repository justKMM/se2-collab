package hbrs.se2.collhbrs.repository;

import hbrs.se2.collhbrs.model.entity.traits.Requirements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequirementsRepository extends JpaRepository<Requirements, Long> {
    List<Requirements> getRequirementsByVacancyVacancyID(Long vacancyId);

    void deleteByVacancy_VacancyID(Long vacancyId);
}
