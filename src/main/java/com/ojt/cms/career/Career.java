package com.ojt.cms.career;

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
public class Career extends BaseEntity {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long careerId;
    
    @Column(nullable = false, length = 100)
    private String companyName;
    
    @Column(nullable = false, length = 100)
    private String rank; //직급
    
    @Column(nullable = false)
    private LocalDate startDate;
    
    @Column(nullable = true)
    private LocalDate endDate;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userId")
    private User user;
}
