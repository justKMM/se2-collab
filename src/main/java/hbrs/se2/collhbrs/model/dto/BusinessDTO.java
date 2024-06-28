package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.model.entity.User;

public class BusinessDTO {

    private long businessID;
    private User user;
    private String name;

    public BusinessDTO(Business entity) {
        this.businessID = entity.getBusinessID();
        this.name = entity.getName();
        this.user = entity.getUser();
    }

    public long getBusinessID() {
        return businessID;
    }

    public void setBusinessID(long businessID) {
        this.businessID = businessID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
