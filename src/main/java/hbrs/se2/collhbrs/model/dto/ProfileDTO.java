package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Profile;

public class ProfileDTO {

    private long profileID;
    private String avatarUrl;
    private String profileDescription;
    private String xingUsername;
    private String linkedinUsername;

    public ProfileDTO(Profile entity) {
        this.profileID = entity.getProfileID();
        this.avatarUrl = entity.getAvatarUrl();
        this.profileDescription = entity.getProfileDescription();
        this.xingUsername = entity.getXingUsername();
        this.linkedinUsername = entity.getLinkedinUsername();
    }

    public long getProfileID() {
        return profileID;
    }

    public void setProfileID(long profileID) {
        this.profileID = profileID;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public String getXingUsername() {
        return xingUsername;
    }

    public void setXingUsername(String xingUsername) {
        this.xingUsername = xingUsername;
    }

    public String getLinkedinUsername() {
        return linkedinUsername;
    }

    public void setLinkedinUsername(String linkedinUsername) {
        this.linkedinUsername = linkedinUsername;
    }
}