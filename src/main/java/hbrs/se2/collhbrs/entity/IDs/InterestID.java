package hbrs.se2.collhbrs.entity.IDs;

import hbrs.se2.collhbrs.entity.Interest;

import java.util.Objects;

public class InterestID {
    private Interest interest;
    private int serialNumber;

    public Interest getInterest() {
        return interest;
    }

    public void setInterest(Interest interest) {
        this.interest = interest;
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
        InterestID interestID = (InterestID) o;
        return serialNumber == interestID.serialNumber &&
                Objects.equals(interest, interestID.interest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interest, serialNumber);
    }
}