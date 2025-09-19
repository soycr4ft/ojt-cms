package com.ojt.cms.license;

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
public class License extends BaseEntity {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long licenseId;

    @Column(nullable = false)
    private Boolean kosa; //kosa소속인지
    
    @Column(nullable = true, length = 255)
    private String licenseName;

    @Column(nullable = true)
    private LocalDate acDate;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userId")
    private User user;
}
