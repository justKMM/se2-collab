package EntityTest;


import hbrs.se2.collhbrs.model.entity.Skill;
import hbrs.se2.collhbrs.model.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SkillTest {

    private final static String TESTING = "Testing";

    Skill skill;
    Skill skill1;

    @Mock
    Student student;

    @BeforeEach
    public void setUp() {
        skill = new Skill();
        skill.setStudent(student);
        skill.setSerialNumber(10);
        skill.setSkillName(TESTING);

        skill1 = new Skill();
        skill1.setStudent(student);
        skill1.setSerialNumber(11);
        skill1.setSkillName("Juggle1");
    }

    @Test
    void testGettersandSetters() {
        assertEquals(student, skill.getStudent());
        assertEquals(10, skill.getSerialNumber());
        assertEquals(TESTING, skill.getSkillName());

        assertEquals(student, skill1.getStudent());
        assertEquals(11, skill1.getSerialNumber());
        assertNotEquals(TESTING, skill1.getSkillName());
    }

    @Test
    void testEquals() {
        assertEquals(skill1, skill1);
        assertEquals(skill, skill);
        assertNotEquals(skill, skill1);
    }

    @Test
    void testHashCode() {
        assertEquals(skill.hashCode(), skill.hashCode());
        assertNotEquals(skill.hashCode(), skill1.hashCode());
    }
}
