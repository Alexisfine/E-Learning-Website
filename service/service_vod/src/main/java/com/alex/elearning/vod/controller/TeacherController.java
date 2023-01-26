package com.alex.elearning.vod.controller;

import com.alex.elearning.dto.TeacherSearchDto;
import com.alex.elearning.entities.vod.Teacher;
import com.alex.elearning.response.Response;
import com.alex.elearning.vod.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vod/teacher")
public class TeacherController {
    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public Response<List<Teacher>> getAllTeachers() {
        return Response.ok(teacherService.getAllTeachers());
    }

    @PostMapping
    public Response<Teacher> addTeacher(@RequestBody Teacher teacher) {
        return Response.ok(teacherService.addTeacher(teacher));
    }

    @DeleteMapping("/{teacherId}")
    public Response deleteTeacher(@PathVariable("teacherId") String id) {
        teacherService.deleteTeacher(id);
        return Response.ok();
    }

    @PostMapping("/search/{current}/{limit}")
    public Response searchTeachers(
            @PathVariable int current,
            @PathVariable int limit,
            @RequestBody(required = false) TeacherSearchDto teacherSearchDto) {
        return Response.ok(teacherService.search(PageRequest.of(current, limit), teacherSearchDto));
    }

    @GetMapping("/{id}")
    public Response<Teacher> getTeacherById(@PathVariable String id) {
        return Response.ok(teacherService.getById(id));
    }

    @PutMapping
    public Response<Teacher> updateTeacherById(@RequestBody Teacher teacher) {
        return Response.ok(teacherService.update(teacher));
    }

    @DeleteMapping
    public Response removeTeachersByIds(@RequestBody List<String> ids) {
        teacherService.removeTeacherByIds(ids);
        return Response.ok();
    }
}
