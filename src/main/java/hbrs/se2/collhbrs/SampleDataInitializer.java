package hbrs.se2.collhbrs;

import hbrs.se2.collhbrs.service.RegisterService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class SampleDataInitializer {

    private final RegisterService registerService;

    public SampleDataInitializer(RegisterService registerService) {
        this.registerService = registerService;
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
