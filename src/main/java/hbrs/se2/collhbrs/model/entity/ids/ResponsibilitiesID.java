package hbrs.se2.collhbrs.model.entity.ids;

import hbrs.se2.collhbrs.model.entity.Vacancy;

import java.io.Serializable;
import java.util.Objects;

public class ResponsibilitiesID implements Serializable {
    private Vacancy vacancy;
    private int serialNumber;

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
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
        ResponsibilitiesID requirementsID = (ResponsibilitiesID) o;
        return serialNumber == requirementsID.serialNumber &&
                Objects.equals(vacancy, requirementsID.vacancy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vacancy, serialNumber);
    }
}