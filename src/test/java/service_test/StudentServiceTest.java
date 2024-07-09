package service_test;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.repository.StudentRepository;
import hbrs.se2.collhbrs.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    private static final Long STUDENT_ID = 1L;
    private static final String OLD_RESUME = "oldResume";
    private static final String NEW_RESUME = "newResume";
    private static final String RESUME_DATA = "resumeData";
    private static final String STUDENT_NOT_FOUND_MESSAGE = "Student with ID " + STUDENT_ID + " not found";

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveResume() {
        Student student = new Student();
        student.setStudentID(STUDENT_ID);
        student.setResume(OLD_RESUME);
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        studentService.saveResume(STUDENT_ID, NEW_RESUME);

        assertEquals(NEW_RESUME, student.getResume());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testSaveResumeStudentNotFound() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                studentService.saveResume(STUDENT_ID, NEW_RESUME));

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(STUDENT_NOT_FOUND_MESSAGE));
    }

    @Test
    void testDeleteResume() {
        Student student = new Student();
        student.setStudentID(STUDENT_ID);
        student.setResume(OLD_RESUME);
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        studentService.deleteResume(STUDENT_ID);

        assertNull(student.getResume());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testDeleteResumeStudentNotFound() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                studentService.deleteResume(STUDENT_ID));

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(STUDENT_NOT_FOUND_MESSAGE));
    }

    @Test
    void testGetResume() {
        Student student = new Student();
        student.setStudentID(STUDENT_ID);
        student.setResume(RESUME_DATA);
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        String resume = studentService.getResume(STUDENT_ID);

        assertEquals(RESUME_DATA, resume);
    }

    @Test
    void testGetResumeStudentNotFound() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                studentService.getResume(STUDENT_ID));

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(STUDENT_NOT_FOUND_MESSAGE));
    }
}