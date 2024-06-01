package hbrs.se2.collhbrs.model.dto.imp;

import hbrs.se2.collhbrs.model.dto.ProfileDTO;
import hbrs.se2.collhbrs.model.entity.Profile;

public class ProfileDTOImpl implements ProfileDTO {
    private long profileID;
    private String avatarUrl;
    private String profileDescription;
    private String xingUsername;
    private String linkedinUsername;

    @Override
    public long getProfileID() {
        return profileID;
    }


    public void setProfileID(long profileID) {
        this.profileID = profileID;
    }

    @Override
    public String getAvatarUrl() {
        return avatarUrl;
    }


    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String getProfileDescription() {
        return profileDescription;
    }


    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    @Override
    public String getXingUsername() {
        return xingUsername;
    }


    public void setXingUsername(String xingUsername) {
        this.xingUsername = xingUsername;
    }

    @Override
    public String getLinkedinUsername() {
        return linkedinUsername;
    }


    public void setLinkedinUsername(String linkedinUsername) {
        this.linkedinUsername = linkedinUsername;
    }


    public Profile getEntity() {
        Profile profile = new Profile();
        profile.setProfileID(profileID);
        profile.setAvatarUrl(avatarUrl);
        profile.setProfileDescription(profileDescription);
        profile.setXingUsername(xingUsername);
        profile.setLinkedinUsername(linkedinUsername);
        return profile;
    }
}