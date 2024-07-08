package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.model.entity.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BusinessDTO extends UserDTO {

    private long businessID;
    private User user;
    private String name;
    private String address;
    private String city;
    private String zipCode;
    private String country;

    public BusinessDTO(Business entity) {
        super(entity.getUser());
        this.user = entity.getUser();
        this.businessID = entity.getBusinessID();
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.city = entity.getCity();
        this.zipCode = entity.getZipCode();
        this.country = entity.getCountry();
    }

    public Business getBusiness() {
        Business business = new Business();
        business.setBusinessID(businessID);
        business.setName(name);
        business.setUser(user);
        business.setAddress(address);
        business.setCity(city);
        business.setZipCode(zipCode);
        business.setCountry(country);
        return business;
    }

    @Override
    public User getUser() {
        return user;
    }

    public String toString() {
        return "BusinessDTO{" +
                "businessID=" + businessID +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

}
