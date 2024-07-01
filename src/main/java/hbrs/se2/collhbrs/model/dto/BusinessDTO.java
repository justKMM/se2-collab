package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.model.entity.User;

public class BusinessDTO extends UserDTO {

    private long businessID;
    private User user;
    private String name;

    public BusinessDTO(Business entity) {
        super(entity.getUser());
        this.user = entity.getUser();
        this.businessID = entity.getBusinessID();
        this.name = entity.getName();
    }

    public Business getBusiness() {
        Business business = new Business();
        business.setBusinessID(businessID);
        business.setName(name);
        business.setUser(user);
        return business;
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

    @Override
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
