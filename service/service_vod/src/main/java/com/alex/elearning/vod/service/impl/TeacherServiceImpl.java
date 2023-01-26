package com.alex.elearning.vod.service.impl;

import com.alex.elearning.dto.TeacherSearchDto;
import com.alex.elearning.entities.vod.Teacher;
import com.alex.elearning.vod.repository.TeacherRepository;
import com.alex.elearning.vod.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    private TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(String id) {
        teacherRepository.softDelete(id);
    }

    @Override
    public Page<Teacher> search(PageRequest pageRequest, TeacherSearchDto teacherSearchDto) {
        if (null == teacherSearchDto) {
            return teacherRepository.findAll(pageRequest);
        }
        Specification<Teacher> specification = new Specification<Teacher>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                String name = teacherSearchDto.getName();
                if (!StringUtils.isEmpty(name)) {
                    predicates.add(criteriaBuilder.like(root.get("name").as(String.class), "%"+name+"%"));
                }

                Integer level = teacherSearchDto.getLevel();
                if (level != null) {
                    predicates.add(criteriaBuilder.equal(root.get("level").as(Integer.class), level));
                }

                String joinDateBegin = teacherSearchDto.getJoinDateBegin();
                if (!StringUtils.isEmpty(joinDateBegin)) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("join_date"), LocalDateTime.parse(joinDateBegin)));
                }
                String joinDateEnd = teacherSearchDto.getJoinDateEnd();
                if (!StringUtils.isEmpty(joinDateEnd)) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("join_date"), joinDateEnd));
                }
                Predicate[] pre = new Predicate[predicates.size()];
                criteriaQuery.where( predicates.toArray( pre ) );
                return criteriaBuilder.and( predicates.toArray( pre ) );
            }
        };
        return teacherRepository.findAll(specification, pageRequest);
    }

    @Override
    public Teacher getById(String id) {
        return teacherRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public Teacher update(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void removeTeacherByIds(List<String> ids) {
        ids
                .stream()
                .map(id -> teacherRepository.findById(id).orElseThrow(() -> new IllegalArgumentException()))
                .forEach(entity -> teacherRepository.softDelete(entity));
    }
}
