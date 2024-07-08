package hbrs.se2.collhbrs.service.registration;

import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.model.entity.Profile;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.model.entity.traits.FirstName;

import java.util.List;

public interface RegisterService {
    void saveUser(User user);

    List<User> getUsers();

    void registerBusiness(String username, String password, String email, String businessName);

    void registerStudent(String username, String password, String email, String firstName, String lastName);

    void saveProfile(Profile profile);

    void saveStudent(Student student);

    void saveVorname(FirstName firstName);

    void saveFirstNames(String[] firstNames, Student student);

    void saveBusiness(Business business);

    Boolean isEmpty();
}