package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.Application;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.traits.FirstName;
import hbrs.se2.collhbrs.repository.ApplicationRepository;
import hbrs.se2.collhbrs.repository.FirstNameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final FirstNameRepository firstNameRepository;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            FirstNameRepository firstNameRepository
    ) {
        this.applicationRepository = applicationRepository;
        this.firstNameRepository = firstNameRepository;
    }

    @Transactional
    public void saveApplication(Application application) {
        applicationRepository.save(application);
    }

    public List<Application> getAllApplications(Long vacanyId) {
        return applicationRepository.findApplicationByVacancy_VacancyID(vacanyId);
    }

    public FirstName getFirstName(Student student) {
        return firstNameRepository.findFirstNameByStudent_StudentID(student.getStudentID());
    }

    public String getCoverLetter(long applicationID) {
        return applicationRepository.findApplicationByApplicationID(applicationID).getCoverLetter();
    }
}
