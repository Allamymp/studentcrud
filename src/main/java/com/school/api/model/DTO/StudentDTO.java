package com.school.api.model.DTO;

public record StudentDTO(String name, Integer age, Float firstSemesterGrade, Float secondSemesterGrade,
                         String teachersName, String roomNumber) {
}
