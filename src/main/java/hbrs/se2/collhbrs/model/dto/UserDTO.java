package hbrs.se2.collhbrs.model.dto;

public interface UserDTO {
    long getUserID();


    ProfileDTO getProfile();


    String getUsername();


    String getPassword();


    int getBlacklisted();


    String getEmail();


}
