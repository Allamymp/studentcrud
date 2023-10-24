package com.school.api.services;

import com.school.api.model.entities.Student;
import com.school.api.model.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServices {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> listStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> searchStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> updateStudent(Long id, Student updatedStudent) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Student existingStudent = student.get();
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setAge(updatedStudent.getAge());
            existingStudent.setFirstSemesterGrade(updatedStudent.getFirstSemesterGrade());
            existingStudent.setSecondSemesterGrade(updatedStudent.getSecondSemesterGrade());
            existingStudent.setTeachersName(updatedStudent.getTeachersName());
            existingStudent.setRoomNumber(updatedStudent.getRoomNumber());
            studentRepository.save(existingStudent);
            return Optional.of(existingStudent);
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}


