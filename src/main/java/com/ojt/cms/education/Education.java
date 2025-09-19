package com.ojt.cms.education;

import com.ojt.cms.common.BaseEntity;
import com.ojt.cms.skillcode.SkillCode;
import com.ojt.cms.user.User;
import com.ojt.cms.userskill.UserSkill;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Education extends BaseEntity {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long eduId;

    @Column(nullable = false, length = 50)
    private Boolean exam; //검정고시인지 아닌지

    @Column(nullable = false, length = 50)
    private String schoolName;

    @Column(nullable = false, length = 50)
    private String level;
}
