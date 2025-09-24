package com.ojt.cms.detail;

import com.ojt.cms.common.BaseEntity;
import com.ojt.cms.detail.enums.UserProjStatus;
import com.ojt.cms.user.User;
import com.ojt.cms.user.dto.ModifyUserInfoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Detail extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long detailId;

    @Column(nullable = false, length = 255)
    private String address1;
    
    @Column(nullable = false, length = 255)
    private String address2; //상세주소

    @Column(nullable = false, length = 255)
    private String profile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private UserProjStatus status;
    
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

	public void modifyUserInfo(ModifyUserInfoDTO dto) {
		this.address1=dto.getAddress1();
		this.address2=dto.getAddress2();
		this.profile=dto.getProfile();
	}
}
