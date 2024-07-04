package DTOsTest;

import hbrs.se2.collhbrs.model.dto.SkillDTO;
import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.User;
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
        User user = new User();
        user.setUserID(1L);
        user.setUsername("testuser");
        Student student = new Student();
        student.setUser(user);
        studentDTO = new StudentDTO(student);
        Skill skill = new Skill();
        skill.setStudent(student);
        skillDTO = new SkillDTO(skill);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(0, skillDTO.getSerialNumber());
        assertNull(skillDTO.getSkillName());
        
        skillDTO.setSerialNumber(123);
        skillDTO.setSkillName("Java Testing");

        assertEquals(studentDTO.getStudent(), skillDTO.getStudent());
        assertEquals(123, skillDTO.getSerialNumber());
        assertEquals("Java Testing", skillDTO.getSkillName());
    }
}
