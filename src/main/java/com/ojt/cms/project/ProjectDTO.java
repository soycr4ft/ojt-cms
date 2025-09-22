package com.ojt.cms.project;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProjectDTO {
    private Long projectId;
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String customer; //고객사
    private String role;
    private String os;
    private String lang;
    private String db;
    private String tool;
    private String etc;
    private Long careerId;
}
