package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.dto.BusinessDTO;
import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.model.dto.VacancyDTO;
import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.repository.BusinessRepository;
import hbrs.se2.collhbrs.repository.VacancyRepository;
import hbrs.se2.collhbrs.util.EntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VacancyService {

    @Autowired
    private VacancyRepository repository;

    @Autowired
    private static BusinessRepository businessRepository;

    public void createVacancy(VacancyDTO vacancyDTO, BusinessDTO businessDTO) {
        Vacancy vacancyEntity = EntityFactory.creatVacancy(vacancyDTO,businessDTO);
        this.repository.save(vacancyEntity);
    }

    public static Business findBusiness(Long businessID) {
       return businessRepository.findBusinessByUser_UserID(businessID);
    }
}
