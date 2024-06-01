package EntityTest;


import hbrs.se2.collhbrs.model.entity.Skill;
import hbrs.se2.collhbrs.model.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

public class SkillTest {

    Skill skill;
    Skill skill1;

    @Mock
    Student student;

    @BeforeEach
    public void setUp() {
        skill = new Skill();
        skill.setStudent(student);
        skill.setSerialNumber(10);
        skill.setSkillName("Juggle");

        skill1 = new Skill();
        skill1.setStudent(student);
        skill1.setSerialNumber(11);
        skill1.setSkillName("Juggle1");
    }

    @Test
    public void testGettersandSetters() {
        assertEquals(student, skill.getStudent());
        assertEquals(10, skill.getSerialNumber());
        assertEquals("Juggle", skill.getSkillName());

        assertEquals(student, skill1.getStudent());
        assertEquals(11, skill1.getSerialNumber());
        assertNotEquals("Juggle", skill1.getSkillName());
    }

    @Test
    public void testEquals() {
        assertEquals(skill1, skill1);
        assertEquals(skill, skill);
        assertNotEquals(skill, skill1);
    }

    @Test
    public void testHashCode() {
        assertEquals(skill.hashCode(), skill.hashCode());
        assertNotEquals(skill.hashCode(), skill1.hashCode());
    }
}
