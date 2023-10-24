package com.school.api.model.entities;


import com.school.api.model.DTO.StudentDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private Float firstSemesterGrade;
    private Float secondSemesterGrade;
    private String teachersName;
    private String  roomNumber;


 public Student (StudentDTO studentDTO){
     this.name = studentDTO.name();
     this.age = studentDTO.age();
     this.firstSemesterGrade = studentDTO.firstSemesterGrade();
     this.secondSemesterGrade = studentDTO.secondSemesterGrade();
     this.teachersName = studentDTO.teachersName();
     this.roomNumber = studentDTO.roomNumber();
 }
}
