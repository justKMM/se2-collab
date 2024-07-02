package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.*;
import hbrs.se2.collhbrs.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProfileService {

    private static final String PROFILE_NOT_FOUND = "Profile with ID ";
    private static final String NOT_FOUND = " not found";
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final InterestRepository interestRepository;
    private final SkillRepository skillRepository;
    private final DegreeProgrammRepository degreeProgrammRepository;
    private final FirstNameRepository firstNameRepository;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository, StudentRepository studentRepository, InterestRepository interestRepository, SkillRepository skillRepository, DegreeProgrammRepository degreeProgrammRepository, FirstNameRepository firstNameRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.interestRepository = interestRepository;
        this.skillRepository = skillRepository;
        this.degreeProgrammRepository = degreeProgrammRepository;
        this.firstNameRepository = firstNameRepository;
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
        Optional<Profile> optionalProfile = profileRepository.findById(profileId);
        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            profile.setAvatar(base64Image);
            profileRepository.save(profile);
        } else {
            throw new IllegalArgumentException(PROFILE_NOT_FOUND + profileId + NOT_FOUND);
        }
    }

    @Transactional
    public String getProfileImage(Long profileId) {
        return profileRepository.findById(profileId)
                .map(Profile::getAvatar)
                .orElseThrow(() -> new IllegalArgumentException(PROFILE_NOT_FOUND + profileId + NOT_FOUND));
    }

    @Transactional
    public void deleteProfileImage(Long profileId) {
        Optional<Profile> optionalProfile = profileRepository.findById(profileId);
        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            profile.setAvatar(null);
            profileRepository.save(profile);
        } else {
            throw new IllegalArgumentException(PROFILE_NOT_FOUND + profileId + NOT_FOUND);
        }
    }
}