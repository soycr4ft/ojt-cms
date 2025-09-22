package com.ojt.cms.education;

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
public class EducationDTO {
    private Long eduId;
    private Boolean exam; //검정고시인지 아닌지
    private String schoolName;
    private EducationType level;
    private String major;
    private LocalDate entDate;
    private LocalDate graduDate;
    private EducationStatus eduStatus;
    private Long userId;
}
