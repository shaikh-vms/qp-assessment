package com.assessment.questionpro.grocery.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.questionpro.grocery.auth.model.UserRole;

public interface RoleRepository extends JpaRepository<UserRole, Long> {
	UserRole findByName(String name);
}
