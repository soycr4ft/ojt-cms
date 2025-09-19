package com.ojt.cms.military;

import java.time.LocalDate;

import com.ojt.cms.common.BaseEntity;
import com.ojt.cms.military.enums.MilitaryDischarge;
import com.ojt.cms.military.enums.MilitaryInfo;
import com.ojt.cms.military.enums.MilitaryRank;
import com.ojt.cms.military.enums.MilitaryType;
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
public class Military extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long militaryId;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MilitaryInfo militaryInfo;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MilitaryType militaryType;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MilitaryRank militaryRank;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MilitaryDischarge dischargeType;

    @Column(nullable = false)
    private LocalDate startMil;

    @Column(nullable = false)
    private LocalDate endMil;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userId")
    private User user;
}
