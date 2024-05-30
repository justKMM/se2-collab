package hbrs.se2.collhbrs.util;

import hbrs.se2.collhbrs.model.entity.*;
import org.springframework.stereotype.Component;

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

    public FirstName createFirstName(String firstName, Student student, int serialNumber) {
        FirstName firstNameEntity = new FirstName();
        firstNameEntity.setFirstNameName(firstName);
        firstNameEntity.setStudent(student);
        firstNameEntity.setSerialNumber(serialNumber);
        return firstNameEntity;
    }


    public Business createBusiness(String name, User user) {
        Business business = new Business();
        business.setName(name);
        business.setUser(user);
        return business;
    }
}
