package hbrs.se2.collhbrs.dto.impl;

import hbrs.se2.collhbrs.dto.BenutzerDTO;
import hbrs.se2.collhbrs.dto.RolleDTO;

import java.util.List;

public class BenutzerDTOImplementation implements BenutzerDTO {
    private int benutzerID;
    private String vorname;
    private String nachname;
    private List<RolleDTO> rollen;

    public void setBenutzerID(int benutzerID) {
        this.benutzerID = benutzerID;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setRollen(List<RolleDTO> rollen) {
        this.rollen = rollen;
    }

    @Override
    public int getID() {
        return this.benutzerID;
    }

    @Override
    public String getVorname() {
        return this.vorname;
    }

    @Override
    public String getNachname() {
        return this.nachname;
    }


    @Override
    public List<RolleDTO> getRollen() {
        return this.rollen;
    }

    @Override
    public String toString() {
        return "BenutzerDTOImplementation{" +
                "BenutzerID=" + benutzerID +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", rollen=" + rollen +
                '}';
    }
}
