package com.alex.elearning.entities.vod;

import com.alex.elearning.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teacher")
public class Teacher extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String name;
    private String career;
    private Integer level;
    private String avatar;
    private Integer sort;
    private LocalDateTime joinDate;
}
