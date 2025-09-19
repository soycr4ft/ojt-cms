package com.ojt.cms.military;

import java.time.LocalDate;

import com.ojt.cms.military.enums.MilitaryDischarge;
import com.ojt.cms.military.enums.MilitaryInfo;
import com.ojt.cms.military.enums.MilitaryRank;
import com.ojt.cms.military.enums.MilitaryType;

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
public class MilitaryDTO {
    private Long militaryId;
    private MilitaryInfo militaryInfo;
    private MilitaryType militaryType;
    private MilitaryRank militaryRank;
    private MilitaryDischarge dischargeType;
    private LocalDate startMil;
    private LocalDate endMil;
    private Long userId;
}
