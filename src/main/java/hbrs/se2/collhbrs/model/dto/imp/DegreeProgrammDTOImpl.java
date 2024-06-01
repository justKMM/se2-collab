package hbrs.se2.collhbrs.model.dto.imp;

import hbrs.se2.collhbrs.model.dto.DegreeProgrammDTO;
import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.entity.DegreeProgramm;

public class DegreeProgrammDTOImpl implements DegreeProgrammDTO {
    private StudentDTO student;
    private int serialNumber;
    private String degreeProgrammName;

    @Override
    public StudentDTO getStudent() {
        return student;
    }

    @Override
    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    @Override
    public int getSerialNumber() {
        return serialNumber;
    }

    @Override
    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String getDegreeProgrammName() {
        return degreeProgrammName;
    }

    @Override
    public void setDegreeProgrammName(String degreeProgrammName) {
        this.degreeProgrammName = degreeProgrammName;
    }

    @Override
    public DegreeProgramm getEntity() {
        DegreeProgramm degreeProgramm = new DegreeProgramm();
        degreeProgramm.setStudent(student.getEntity());
        degreeProgramm.setSerialNumber(serialNumber);
        degreeProgramm.setDegreeProgrammName(degreeProgrammName);
        return degreeProgramm;
    }
}