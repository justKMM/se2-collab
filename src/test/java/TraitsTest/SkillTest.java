package TraitsTest;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.ids.SkillID;
import hbrs.se2.collhbrs.model.entity.traits.Skill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SkillTest {

    private Skill skill;
    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setStudentID(1L);
        student.setLastName("Test");

        skill = new Skill();
        skill.setStudent(student);
        skill.setSerialNumber(123);
        skill.setSkillName("Programming");
    }

    @Test
    void testGetSerialNumber() {
        assertEquals(123, skill.getSerialNumber());
    }

    @Test
    void testSetSerialNumber() {
        skill.setSerialNumber(456);
        assertEquals(456, skill.getSerialNumber());
    }

    @Test
    void testGetSkillName() {
        assertEquals("Programming", skill.getSkillName());
    }

    @Test
    void testSetSkillName() {
        skill.setSkillName("Database Management");
        assertEquals("Database Management", skill.getSkillName());
    }

    @Test
    void testGetId() {
        SkillID id = skill.getId();
        assertEquals(student, id.getStudent());
        assertEquals(123, id.getSerialNumber());
    }

    @Test
    void testSkillNotNull() {
        assertNotNull(skill);
    }

    @Test
    void testGetStudent() {
        assertEquals(student, skill.getStudent());
    }

    @Test
    void testSetStudent() {
        Student newStudent = new Student();
        newStudent.setStudentID(2L);
        newStudent.setLastName("Schmidt");
        skill.setStudent(newStudent);
        assertEquals(newStudent, skill.getStudent());
    }
}

