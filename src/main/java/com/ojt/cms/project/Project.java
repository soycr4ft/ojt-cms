package com.ojt.cms.project;

import java.time.LocalDate;

import com.ojt.cms.career.Career;
import com.ojt.cms.common.BaseEntity;

import jakarta.persistence.Column;
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
public class Project extends BaseEntity {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long projectId;
    
    @Column(nullable = false, length = 200)
    private String projectName;
    
    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = true)
    private LocalDate endDate;

    @Column(nullable = true, length = 200)
    private String customer; //고객사

    @Column(nullable = true, length = 100)
    private String role;

    @Column(nullable = true, length = 2000)
    private String os;

    @Column(nullable = true, length = 200)
    private String lang;

    @Column(nullable = true, length = 200)
    private String db;

    @Column(nullable = true, length = 200)
    private String tool;

    @Column(nullable = true, length = 500)
    private String etc;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="careerId")
    private Career career;
}
