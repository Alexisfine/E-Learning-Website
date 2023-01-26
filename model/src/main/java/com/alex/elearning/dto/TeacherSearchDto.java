package com.alex.elearning.dto;

import lombok.Data;

@Data
public class TeacherSearchDto {
    private String name;
    private Integer level;
    private String joinDateBegin;
    private String joinDateEnd;
}
