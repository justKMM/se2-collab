package DTOsTest;

import hbrs.se2.collhbrs.model.dto.SkillDTO;
import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.traits.Skill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SkillDTOTest {

    @Mock
    StudentDTO studentDTO;
    private SkillDTO skillDTO;

    @BeforeEach
    void setUp() {
        skillDTO = new SkillDTO(new Skill());
        studentDTO = new StudentDTO(new Student());
    }

    @Test
    void testGettersAndSetters() {
        assertNull(skillDTO.getStudent());
        assertEquals(0, skillDTO.getSerialNumber());
        assertNull(skillDTO.getSkillName());

        skillDTO.setStudent(studentDTO.getStudent());
        skillDTO.setSerialNumber(123);
        skillDTO.setSkillName("Java Testing");

        assertEquals(studentDTO.getStudent(), skillDTO.getStudent());
        assertEquals(123, skillDTO.getSerialNumber());
        assertEquals("Java Testing", skillDTO.getSkillName());
    }
}

