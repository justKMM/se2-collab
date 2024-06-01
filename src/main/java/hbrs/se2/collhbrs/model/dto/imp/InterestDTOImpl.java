package hbrs.se2.collhbrs.model.dto.imp;

import hbrs.se2.collhbrs.model.dto.InterestDTO;
import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.entity.Interest;

public class InterestDTOImpl implements InterestDTO {
    private StudentDTOImpl student;
    private int serialNumber;
    private String interestName;

    @Override
    public StudentDTO getStudent() {
        return student;
    }


    public void setStudent(StudentDTOImpl student) {
        this.student = student;
    }

    @Override
    public int getSerialNumber() {
        return serialNumber;
    }


    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String getInterestName() {
        return interestName;
    }


    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }


    public Interest getEntity() {
        Interest interest = new Interest();
        interest.setStudent(student.getEntity());
        interest.setSerialNumber(serialNumber);
        interest.setInterestName(interestName);
        return interest;
    }
}