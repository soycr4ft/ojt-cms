package com.ojt.cms.education;

import java.time.LocalDate;

import com.ojt.cms.common.BaseEntity;
import com.ojt.cms.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Education extends BaseEntity {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long eduId;

    @Column(nullable = false)
    private Boolean exam; //검정고시인지 아닌지

    @Column(nullable = false, length = 100)
    private String schoolName;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EducationType level;

    @Column(nullable = false, length = 100)
    private String major;

    @Column(nullable = false)
    private LocalDate entDate;

    @Column(nullable = true)
    private LocalDate graduDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EducationStatus eduStatus;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userId")
    private User user;
}
