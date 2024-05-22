package hbrs.se2.collhbrs.entity.IDs;

import hbrs.se2.collhbrs.entity.DegreeProgramm;

import java.util.Objects;

public class DegreeProgrammID {
    private DegreeProgramm degreeProgramm;
    private int serialNumber;

    public DegreeProgramm getDegreeProgramm() {
        return degreeProgramm;
    }

    public void setDegreeProgramm(DegreeProgramm degreeProgramm) {
        this.degreeProgramm = degreeProgramm;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DegreeProgrammID degreeProgrammID = (DegreeProgrammID) o;
        return serialNumber == degreeProgrammID.serialNumber &&
                Objects.equals(degreeProgramm, degreeProgrammID.degreeProgramm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(degreeProgramm, serialNumber);
    }
}