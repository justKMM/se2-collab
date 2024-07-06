package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.model.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class BusinessDTO extends UserDTO implements Serializable {

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

    @Override
    public User getUser() {
        return user;
    }

}
