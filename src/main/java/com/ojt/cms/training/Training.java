package com.ojt.cms.training;

import java.time.LocalDate;

import com.ojt.cms.common.BaseEntity;
import com.ojt.cms.user.User;

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
public class Training extends BaseEntity {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long trainId;

    @Column(nullable = false, length = 255)
    private String course; //교육과정명

    @Column(nullable = false, length = 255)
    private String trainName; //교육기관명

    @Column(nullable = false)
    private LocalDate startTrain;

    @Column(nullable = false)
    private LocalDate endTrain;

    @Column(nullable = false, length = 50)
    private String status; //수료,말고 뭐가 있나 근데
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userId")
    private User user;
}
