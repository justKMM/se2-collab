package hbrs.se2.collhbrs.repository;

import hbrs.se2.collhbrs.model.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findApplicationByVacancy_VacancyID(long vacancyID);
}
