package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.model.dto.VacancyDTO;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.repository.VacancyRepository;
import hbrs.se2.collhbrs.util.EntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VacancyControl {

    @Autowired
    private VacancyRepository repository;

    public void createVacancy(VacancyDTO vacancyDTO, UserDTO userDTO) {
        Vacancy vacancyEntity = EntityFactory.creatVacancy(vacancyDTO,userDTO);
        this.repository.save(vacancyEntity);
    }
}
