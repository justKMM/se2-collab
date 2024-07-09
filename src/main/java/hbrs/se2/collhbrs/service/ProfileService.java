package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.model.entity.Profile;
import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.repository.BusinessRepository;
import hbrs.se2.collhbrs.repository.ProfileRepository;
import hbrs.se2.collhbrs.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class ProfileService implements Serializable {

    private static final String PROFILE_NOT_FOUND = "Profile with ID ";
    private static final String NOT_FOUND = " not found";

    private final transient ProfileRepository profileRepository;
    private final transient UserRepository userRepository;
    private final transient BusinessRepository businessRepository;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository, BusinessRepository businessRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.businessRepository = businessRepository;
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
    public void saveLinkedInUsername(Long profileId, String linkedinUsername) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException(PROFILE_NOT_FOUND + profileId + NOT_FOUND));
        profile.setLinkedinUsername(linkedinUsername);
        profileRepository.save(profile);
    }

    @Transactional
    public Business getBusinessProfile(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return businessRepository.findBusinessByUserUserID(user.getUserID());
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
