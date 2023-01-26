package com.alex.elearning.vod.service;

import com.alex.elearning.dto.TeacherSearchDto;
import com.alex.elearning.entities.vod.Teacher;
import com.alex.elearning.response.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeachers();

    Teacher addTeacher(Teacher teacher);

    void deleteTeacher(String id);

    Page<Teacher> search(PageRequest of, TeacherSearchDto teacherSearchDto);

    Teacher getById(String id);

    Teacher update( Teacher teacher);

    void removeTeacherByIds(List<String> ids);
}
