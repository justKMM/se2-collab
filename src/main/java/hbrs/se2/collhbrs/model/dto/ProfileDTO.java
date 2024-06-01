package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Profile;

public interface ProfileDTO {
    long getProfileID();

    String getAvatarUrl();

    String getProfileDescription();

    String getXingUsername();

    String getLinkedinUsername();

}