package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.dto.BusinessDTO;
import hbrs.se2.collhbrs.model.dto.VacancyDTO;
import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.repository.BusinessRepository;
import hbrs.se2.collhbrs.repository.VacancyRepository;
import hbrs.se2.collhbrs.util.EntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class VacancyService {

    @Autowired
    private VacancyRepository repository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private EntityFactory entityFactory;

    @Transactional
    public void saveVacancy(VacancyDTO vacancyDTO, BusinessDTO businessDTO) {
        Vacancy vacancyEntity = entityFactory.creatVacancy(vacancyDTO, findBusiness(businessDTO));
        System.out.println(vacancyEntity.getBusiness().getBusinessID());
        this.repository.save(vacancyEntity);
    }

    private Business findBusiness(BusinessDTO businessDTO) {
        return businessRepository.findBusinessByUser_UserID(businessDTO.getUser().getUserID());
    }
}
