package hbrs.se2.collhbrs.dto.impl;

import hbrs.se2.collhbrs.dto.RolleDTO;

public class RolleDTOImplementation implements RolleDTO {
    private String role;
    @Override
    public String getRolle() {
        return this.role;
    }
}
