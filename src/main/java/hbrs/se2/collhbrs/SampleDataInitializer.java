package hbrs.se2.collhbrs;

import hbrs.se2.collhbrs.model.dto.BusinessDTO;
import hbrs.se2.collhbrs.model.dto.VacancyDTO;
import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.repository.BusinessRepository;

import hbrs.se2.collhbrs.service.RegisterService;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.service.VacancyService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Profile("dev")
@Component
public class SampleDataInitializer {

    private final RegisterService registerService;
    private final VacancyService vacancyService;
    private final SessionService sessionService;


    public SampleDataInitializer(RegisterService registerService, VacancyService vacancyService, SessionService sessionService) {
        this.registerService = registerService;
        this.vacancyService = vacancyService;
        this.sessionService = sessionService;
    }

    @PostConstruct
    public void init() {
        if (Boolean.TRUE.equals(registerService.isEmpty())) {
            registerService.registerStudent("admin", "Password1", "admin@example.com", "Admin", "Madmin");
            registerService.registerStudent("spongi", "Password2", "squarepants@example.com", "Spongebob", "Squarepants");
            registerService.registerBusiness("krustykrab", "Password3", "krustykrab@example.com", "KrustyKrab");
        }
    }
}
