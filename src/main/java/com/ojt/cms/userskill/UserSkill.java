package com.ojt.cms.userskill;


import com.ojt.cms.common.BaseEntity;
import com.ojt.cms.skillcode.SkillCode;
import com.ojt.cms.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSkill extends BaseEntity {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userSkillId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="skillCodeId")
    private SkillCode skillCode;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userId")
    private User user;
}
