package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Profile;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ProfileDTO implements Serializable {

    private long profileID;
    private String avatarUrl;
    private String profileDescription;
    private String xingUsername;
    private String linkedinUsername;

    public ProfileDTO(Profile entity) {
        this.profileID = entity.getProfileID();
        this.avatarUrl = entity.getAvatar();
        this.profileDescription = entity.getProfileDescription();
        this.xingUsername = entity.getXingUsername();
        this.linkedinUsername = entity.getLinkedinUsername();
    }

}