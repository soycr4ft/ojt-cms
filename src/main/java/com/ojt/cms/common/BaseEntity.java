package com.ojt.cms.common;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.ojt.cms.department.Department;
import com.ojt.cms.user.enums.ApprovedStatus;
import com.ojt.cms.user.enums.AuthRole;
import com.ojt.cms.user.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@MappedSuperclass //이 클래스는 엔티티로 직접 쓰지 않고, 다른 엔티티가 상속받을 때 공통 매핑 정보만 전달하는 용도
@EntityListeners(AuditingEntityListener.class) //JPA의 Auditing 켜기
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {
	@CreatedDate //insert시 자동 기록
	@Column(updatable = false, nullable=false)
	private LocalDateTime createdAt;
	
	@LastModifiedDate //update시 자동 갱신
	@Column(nullable=false)
	private LocalDateTime updatedAt;
	
	
}
