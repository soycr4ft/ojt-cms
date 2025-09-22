package com.ojt.cms.career;

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
public class CareerDTO {
    private Long careerId;
    private String companyName;
    private String rank; //직급
    private LocalDate startDate;
    private LocalDate endDate;
    private Long userId;
}
