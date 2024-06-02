package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Profile;
import hbrs.se2.collhbrs.model.entity.User;

public class UserDTO {

    private long userID;
    private Profile profile;
    private String username;
    private String password;
    private int blacklisted;
    private String email;


    public UserDTO(User entity) {
        this.userID = entity.getUserID();
        this.profile = entity.getProfile();
        this.username = entity.getUsername();
        this.password = entity.getPassword();
        this.blacklisted = entity.getBlacklisted();
        this.email = entity.getEmail();
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBlacklisted() {
        return blacklisted;
    }

    public void setBlacklisted(int blacklisted) {
        this.blacklisted = blacklisted;
    }
}