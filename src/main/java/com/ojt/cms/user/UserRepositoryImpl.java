package com.ojt.cms.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.ojt.cms.department.QDepartment;
import com.ojt.cms.user.dto.ApprovedUserResponseDTO;
import com.ojt.cms.user.dto.ApprovedUserSearchDTO;
import com.ojt.cms.user.enums.ApprovedStatus;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Page<ApprovedUserResponseDTO> findUserByCondition(ApprovedUserSearchDTO dto, Pageable pageable)
			throws Exception {
		QUser u = QUser.user;
		QDepartment d = QDepartment.department;
		
		List<ApprovedUserResponseDTO> content = jpaQueryFactory
				.select(Projections.constructor(ApprovedUserResponseDTO.class,
						d.deptName,
						u.userId,
						u.loginId,
						u.name,
						u.createdAt,
						u.approved,
						u.auth
				))
				.from(u)
				.leftJoin(u.department,d)
                .where(
                        keywordContains(dto.getType(), dto.getKeyword(), u, d),
                        approvedEq(dto.getApproved())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(u.createdAt.desc())
                .fetch();
		
		//전체 개수
        long total = jpaQueryFactory
                .select(u.count())
                .from(u)
                .leftJoin(u.department, d)
                .where(
                        keywordContains(dto.getType(), dto.getKeyword(), u, d),
                        approvedEq(dto.getApproved())
                )
                .fetchOne();
        return new PageImpl<>(content, pageable, total);
	}
	
	// --- 검색 조건 분리 ---
    private BooleanExpression keywordContains(String type, String keyword, QUser u, QDepartment d) {
        if (keyword == null || keyword.isBlank()) return null;

        return switch (type) {
            case "loginId" -> u.loginId.containsIgnoreCase(keyword);
            case "name"    -> u.name.containsIgnoreCase(keyword);
            case "deptName"-> d.deptName.containsIgnoreCase(keyword);
            default        -> null;
        };
    }

    private BooleanExpression approvedEq(ApprovedStatus status) {
        if (status == null || status == ApprovedStatus.ALL) return null;
        return QUser.user.approved.eq(status);
    }

}
