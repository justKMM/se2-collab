package hbrs.se2.collhbrs.util;

import hbrs.se2.collhbrs.model.entity.*;
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

    public DegreeProgramm createDegreeProgramm(String name, Student student) {
        DegreeProgramm degreeProgramm = new DegreeProgramm();
        degreeProgramm.setDegreeProgrammName(name);
        degreeProgramm.setStudent(student);
        return degreeProgramm;
    }

    public Interest createInterest(String name, Student student) {
        Interest interest = new Interest();
        interest.setInterestName(name);
        interest.setStudent(student);
        return interest;
    }

    public Skill createSkill(String name, Student student) {
        Skill skill = new Skill();
        skill.setSkillName(name);
        skill.setStudent(student);
        return skill;
    }

    public Vacancy createVacancy(String titel, String location, String description, Business business, Date date) {
        Vacancy vacancy = new Vacancy();
        vacancy.setTitle(titel);
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
}
