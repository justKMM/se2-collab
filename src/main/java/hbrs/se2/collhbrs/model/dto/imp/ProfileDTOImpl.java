package hbrs.se2.collhbrs.model.dto.imp;

import hbrs.se2.collhbrs.model.dto.ProfileDTO;

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

    @Override
    public void setProfileID(long profileID) {
        this.profileID = profileID;
    }

    @Override
    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Override
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String getProfileDescription() {
        return profileDescription;
    }

    @Override
    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    @Override
    public String getXingUsername() {
        return xingUsername;
    }

    @Override
    public void setXingUsername(String xingUsername) {
        this.xingUsername = xingUsername;
    }

    @Override
    public String getLinkedinUsername() {
        return linkedinUsername;
    }

    @Override
    public void setLinkedinUsername(String linkedinUsername) {
        this.linkedinUsername = linkedinUsername;
    }
}