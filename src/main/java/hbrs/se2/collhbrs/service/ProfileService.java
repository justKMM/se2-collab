package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.*;
import hbrs.se2.collhbrs.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InterestRepository interestRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private DegreeProgrammRepository degreeProgrammRepository;

    @Autowired
    private FirstNameRepository firstNameRepository;

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Transactional
    public void saveProfile(Profile profile) {
        profileRepository.save(profile);
    }

    @Transactional
    public void saveInterest(Interest interest) {
        interestRepository.save(interest);
    }

    @Transactional
    public void saveSkill(Skill skill) {
        skillRepository.save(skill);
    }

    @Transactional
    public void saveDegreeProgramm(DegreeProgramm degreeProgramm) {
        degreeProgrammRepository.save(degreeProgramm);
    }

    @Transactional
    public void saveFirstName(FirstName firstName) {
        firstNameRepository.save(firstName);
    }

    @Transactional
    public void saveProfileImage(Long profileId, String base64Image) {
        Profile profile = profileRepository.findById(profileId).get();
        profile.setAvatar(base64Image);
        profileRepository.save(profile);
    }

    @Transactional
    public String getProfileImage(Long profileId) {
        return profileRepository.findById(profileId).get().getAvatar();
    }

    @Transactional
    public void deleteProfileImage(Long businessId) {
        Profile profile = profileRepository.findById(businessId).get();
        profile.setAvatar(null);
        profileRepository.save(profile);
    }
}