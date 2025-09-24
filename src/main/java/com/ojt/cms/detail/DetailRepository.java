package com.ojt.cms.detail;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ojt.cms.user.User;

public interface DetailRepository extends JpaRepository<Detail, Long> {
	Optional<Detail> findByUser(User user) throws Exception;
}
