package com.ojt.cms.training;

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
public class TrainingDTO {
    private Long trainId;
    private String course; //교육과정명
    private String trainName; //교육기관명
    private LocalDate startTrain;
    private LocalDate endTrain;
    private String status; //수료,말고 뭐가 있나 근데
    private Long userId;
}
