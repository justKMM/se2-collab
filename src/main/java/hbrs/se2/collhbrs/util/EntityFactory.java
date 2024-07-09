package hbrs.se2.collhbrs.util;

import hbrs.se2.collhbrs.model.entity.*;
import hbrs.se2.collhbrs.model.entity.traits.FirstName;
import hbrs.se2.collhbrs.model.entity.traits.Requirements;
import hbrs.se2.collhbrs.model.entity.traits.Responsibilities;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class EntityFactory {

    public Profile createProfile() {
        return new Profile();
    }

    public User createUser(Profile profile, String username, String password, String email) {
        User user = new User();
        user.setProfile(profile);
        user.setUsername(username);
        user.setPassword(password);
        user.setBlacklisted(0);
        user.setEmail(email);
        return user;
    }

    public Student createStudent(User user, String lastName) {
        Student student = new Student();
        student.setUser(user);
        student.setLastName(lastName);
        return student;
    }

    public FirstName createFirstName(String firstName, Student student) {
        FirstName firstNameEntity = new FirstName();
        firstNameEntity.setFirstNameName(firstName);
        firstNameEntity.setStudent(student);
        return firstNameEntity;
    }


    public Business createBusiness(String name, User user) {
        Business business = new Business();
        business.setName(name);
        business.setUser(user);
        return business;
    }

    public Vacancy createVacancy(String employmentType, String title, String location, String description, Business business, Date date) {
        Vacancy vacancy = new Vacancy();
        vacancy.setTitle(title);
        vacancy.setEmploymentType(employmentType);
        vacancy.setLocation(location);
        vacancy.setDescription(description);
        vacancy.setBusiness(business);
        vacancy.setPublishDate(date);
        return vacancy;
    }

    public Requirements createRequirements(Vacancy vacancy, String requirementsName) {
        Requirements requirements = new Requirements();
        requirements.setVacancy(vacancy);
        requirements.setRequirementsName(requirementsName);
        return requirements;
    }

    public Responsibilities createResponsibilities(Vacancy vacancy, String responsibilitiesName) {
        Responsibilities responsibilities = new Responsibilities();
        responsibilities.setVacancy(vacancy);
        responsibilities.setResponsibilitiesName(responsibilitiesName);
        return responsibilities;
    }

    public Application createApplication(Vacancy vacancy, Student student, String base64Letter) {
        Application application = new Application();
        application.setVacancy(vacancy);
        application.setStudent(student);
        application.setCoverLetter(base64Letter);
        return application;
    }
}
