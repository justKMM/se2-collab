package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {


    private static final String STUDENT_NOT_FOUND = "Student with ID ";
    private static final String NOT_FOUND = " not found";

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public void saveResume(Long studentID, String base64Resume) {
        Optional<Student> optionalStudent = studentRepository.findById(studentID);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setResume(base64Resume);
            studentRepository.save(student);
        } else {
            throw new IllegalArgumentException(STUDENT_NOT_FOUND + studentID + NOT_FOUND);
        }
    }

    public void deleteResume(Long studentID) {
        Optional<Student> optionalStudent = studentRepository.findById(studentID);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setResume(null);
            studentRepository.save(student);
        } else {
            throw new IllegalArgumentException(STUDENT_NOT_FOUND + studentID + NOT_FOUND);
        }
    }

    public String getResume(Long studentID) {
        Optional<Student> optionalStudent = studentRepository.findById(studentID);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            return student.getResume();
        } else {
            throw new IllegalArgumentException(STUDENT_NOT_FOUND + studentID + NOT_FOUND);
        }
    }
}