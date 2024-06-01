package hbrs.se2.collhbrs.model.dto.imp;

import hbrs.se2.collhbrs.model.dto.ProfileDTO;
import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.model.entity.User;

public class UserDTOImpl implements UserDTO {
    private long userID;
    private ProfileDTOImpl profileDTO;
    private String username;
    private String password;
    private int blacklisted;
    private String email;

    @Override
    public long getUserID() {
        return userID;
    }


    public void setUserID(long userID) {
        this.userID = userID;
    }

    @Override
    public ProfileDTO getProfile() {
        return profileDTO;
    }


    public void setProfile(ProfileDTOImpl profileDTO) {
        this.profileDTO = profileDTO;
    }

    @Override
    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int getBlacklisted() {
        return blacklisted;
    }


    public void setBlacklisted(int blacklisted) {
        this.blacklisted = blacklisted;
    }

    @Override
    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public User getEntity() {
        User user = new User();
        user.setUserID(userID);
        user.setProfile(profileDTO.getEntity());
        user.setUsername(username);
        user.setPassword(password);
        user.setBlacklisted(blacklisted);
        user.setEmail(email);
        return user;
    }


}