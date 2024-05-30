package hbrs.se2.collhbrs;

import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.model.entity.FirstName;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.service.RegisterService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

//Profile import conflict
@Profile("dev")
@Component
public class SampleDataInitializer {

    private final RegisterService registerService;

    public SampleDataInitializer(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostConstruct
    public void init() {
        if (registerService.isEmpty()) {
            createSampleStudent("Admin", "Madmin", "admin", "admin@example.com", "Password1");
            createSampleStudent("Spongebob", "Squarepants", "spongi", "squarepants@example.com", "Password2");
            createSampleBusiness("KrustyKrab", "krustykrab", "krustykrab@example.com", "Password3");
        }
    }

    private void createSampleStudent(String firstName, String lastName, String username, String email, String password) {

        //Refactor from "Profile" to e.g "UserProfile"
        hbrs.se2.collhbrs.model.entity.Profile profile = new hbrs.se2.collhbrs.model.entity.Profile();

        User user = new User();
        user.setProfile(profile);
        user.setUsername(username);
        user.setPassword(password);
        user.setBlacklisted(0);
        user.setEmail(email);

        registerService.saveProfile(profile);
        registerService.saveUser(user);

        Student student = new Student();
        student.setUser(user);
        student.setLastName(lastName);

        registerService.saveStudent(student);

        String[] firstNames = firstName.split(" ");
        for (int i = 0; i < firstNames.length; i++) {
            FirstName firstNameEntity = new FirstName();
            firstNameEntity.setFirstNameName(firstNames[i]);
            firstNameEntity.setStudent(student);
            firstNameEntity.setSerialNumber(i);
            registerService.saveVorname(firstNameEntity);
        }
    }

    private void createSampleBusiness(String businessName, String username, String email, String password) {

        hbrs.se2.collhbrs.model.entity.Profile profile = new hbrs.se2.collhbrs.model.entity.Profile();

        User user = new User();
        user.setProfile(profile);
        user.setUsername(username);
        user.setPassword(password);
        user.setBlacklisted(0);
        user.setEmail(email);

        registerService.saveProfile(profile);
        registerService.saveUser(user);

        Business business = new Business();
        business.setName(businessName);
        business.setUser(user);

        registerService.saveBusiness(business);
    }
}
