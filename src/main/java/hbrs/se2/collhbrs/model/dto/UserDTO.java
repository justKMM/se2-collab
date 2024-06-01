package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.User;

public interface UserDTO {
    long getUserID();


    ProfileDTO getProfile();


    String getUsername();


    String getPassword();


    int getBlacklisted();


    String getEmail();


}
