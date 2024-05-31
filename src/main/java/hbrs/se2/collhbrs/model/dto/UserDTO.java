package hbrs.se2.collhbrs.model.dto;

public interface UserDTO {
    long getUserID();

    void setUserID(long userID);

    ProfileDTO getProfile();

    void setProfile(ProfileDTO profileDTO);

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    int getBlacklisted();

    void setBlacklisted(int blacklisted);

    String getEmail();

    void setEmail(String email);
}
