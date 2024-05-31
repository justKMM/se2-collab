package hbrs.se2.collhbrs.model.dto;

public interface ProfileDTO {
    long getProfileID();

    void setProfileID(long profileID);

    String getAvatarUrl();

    void setAvatarUrl(String avatarUrl);

    String getProfileDescription();

    void setProfileDescription(String profileDescription);

    String getXingUsername();

    void setXingUsername(String xingUsername);

    String getLinkedinUsername();

    void setLinkedinUsername(String linkedinUsername);
}