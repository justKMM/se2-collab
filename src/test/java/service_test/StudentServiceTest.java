package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class StudentServiceTest {

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
        student.setStudentID(1L);
        student.setResume("oldResume");
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        studentService.saveResume(1L, "newResume");

        assertEquals("newResume", student.getResume());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testSaveResumeStudentNotFound() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            studentService.saveResume(1L, "newResume");
        });

        String expectedMessage = "Student with ID 1 not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testDeleteResume() {
        Student student = new Student();
        student.setStudentID(1L);
        student.setResume("oldResume");
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        studentService.deleteResume(1L);

        assertNull(student.getResume());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testDeleteResumeStudentNotFound() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            studentService.deleteResume(1L);
        });

        String expectedMessage = "Student with ID 1 not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testGetResume() {
        Student student = new Student();
        student.setStudentID(1L);
        student.setResume("resumeData");
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        String resume = studentService.getResume(1L);

        assertEquals("resumeData", resume);
    }

    @Test
    void testGetResumeStudentNotFound() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            studentService.getResume(1L);
        });

        String expectedMessage = "Student with ID 1 not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}