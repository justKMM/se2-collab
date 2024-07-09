package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.model.entity.Profile;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.model.entity.traits.DegreeProgramm;
import hbrs.se2.collhbrs.model.entity.traits.FirstName;
import hbrs.se2.collhbrs.model.entity.traits.Interest;
import hbrs.se2.collhbrs.model.entity.traits.Skill;
import hbrs.se2.collhbrs.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;

@Service
public class ProfileService implements Serializable {

    private static final String PROFILE_NOT_FOUND = "Profile with ID ";
    private static final String NOT_FOUND = " not found";

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final InterestRepository interestRepository;
    private final SkillRepository skillRepository;
    private final DegreeProgrammRepository degreeProgrammRepository;
    private final FirstNameRepository firstNameRepository;
    private final BusinessRepository businessRepository;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository, StudentRepository studentRepository, InterestRepository interestRepository, SkillRepository skillRepository, DegreeProgrammRepository degreeProgrammRepository, FirstNameRepository firstNameRepository, BusinessRepository businessRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.interestRepository = interestRepository;
        this.skillRepository = skillRepository;
        this.degreeProgrammRepository = degreeProgrammRepository;
        this.firstNameRepository = firstNameRepository;
        this.businessRepository = businessRepository;
    }

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
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException(PROFILE_NOT_FOUND + profileId + NOT_FOUND));
        profile.setAvatar(base64Image);
        profileRepository.save(profile);
    }

    @Transactional
    public String getProfileImage(Long profileId) {
        return profileRepository.findById(profileId)
                .map(Profile::getAvatar)
                .orElseThrow(() -> new IllegalArgumentException(PROFILE_NOT_FOUND + profileId + NOT_FOUND));
    }

    @Transactional
    public void deleteProfileImage(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException(PROFILE_NOT_FOUND + profileId + NOT_FOUND));
        profile.setAvatar(null);
        profileRepository.save(profile);
    }

    @Transactional
    public void saveXingUsername(Long profileId, String xingUsername) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException(PROFILE_NOT_FOUND + profileId + NOT_FOUND));
        profile.setXingUsername(xingUsername);
        profileRepository.save(profile);
    }

    @Transactional
    public String getXingUsername(Long profileId) {
        return profileRepository.findById(profileId)
                .map(Profile::getXingUsername)
                .orElseThrow(() -> new IllegalArgumentException(PROFILE_NOT_FOUND + profileId + NOT_FOUND));
    }

    @Transactional
    public void saveLinkedInUsername(Long profileId, String linkedinUsername) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException(PROFILE_NOT_FOUND + profileId + NOT_FOUND));
        profile.setLinkedinUsername(linkedinUsername);
        profileRepository.save(profile);
    }

    @Transactional
    public String getLinkedinUsername(Long profileId) {
        return profileRepository.findById(profileId)
                .map(Profile::getLinkedinUsername)
                .orElseThrow(() -> new IllegalArgumentException(PROFILE_NOT_FOUND + profileId + NOT_FOUND));
    }

    @Transactional
    public Business getBusinessProfile(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return user.getBusiness();
    }

    @Transactional
    public User findUserById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Transactional
    public void saveBusinessProfile(Business business) {
        businessRepository.save(business);
    }
}
