package hbrs.se2.collhbrs.dto;

import java.util.List;

public interface BenutzerDTO {
    public void setBenutzerID(int benutzerID);
    public void setVorname(String vorname);
    public void setNachname(String nachname);
    public void setRollen(List<RolleDTO> rollen);

    public int getID();
    public String getVorname();
    public String getNachname();
    public List<RolleDTO> getRollen();
}
