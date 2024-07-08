package hbrs.se2.collhbrs.service.registration;

import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.model.entity.Profile;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.model.entity.traits.FirstName;
import hbrs.se2.collhbrs.util.RegisterUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterProxy implements RegisterService {
    private final RegisterServiceImpl registerService;

    public RegisterProxy(RegisterServiceImpl registerService) {
        this.registerService = registerService;
    }

    @Override
    public void saveUser(User user) {
        registerService.saveUser(user);
    }

    @Override
    public List<User> getUsers() {
        return registerService.getUsers();
    }

    @Override
    public void registerBusiness(String username, String password, String email, String businessName) {
        if (RegisterUtils.validateInput(username, businessName, email, password, password)) {
            registerService.registerBusiness(username, password, email, businessName);
        } else {
            throw new RegisterBusinessException("Ungültige Registrierungsdaten für Unternehmen.");
        }
    }

    @Override
    public void registerStudent(String username, String password, String email, String firstName, String lastName) {
        if (RegisterUtils.validateInput(username, firstName, lastName, email, password, password)) {
            registerService.registerStudent(username, password, email, firstName, lastName);
        } else {
            throw new RegisterStudentException("Ungültige Registrierungsdaten für Studenten.");
        }
    }

    @Override
    public void saveProfile(Profile profile) {
        registerService.saveProfile(profile);
    }

    @Override
    public void saveStudent(Student student) {
        registerService.saveStudent(student);
    }

    @Override
    public void saveVorname(FirstName firstName) {
        registerService.saveVorname(firstName);
    }

    @Override
    public void saveFirstNames(String[] firstNames, Student student) {
        registerService.saveFirstNames(firstNames, student);
    }

    @Override
    public void saveBusiness(Business business) {
        registerService.saveBusiness(business);
    }

    @Override
    public Boolean isEmpty() {
        return registerService.isEmpty();
    }

    public static class RegisterStudentException extends RuntimeException {
        public RegisterStudentException(String message) {
            super(message);
        }
    }

    public static class RegisterBusinessException extends RuntimeException {
        public RegisterBusinessException(String message) {
            super(message);
        }
    }
}