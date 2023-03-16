package com.gestion.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestion.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	@Query("SELECT p FROM Role p WHERE CONCAT(p.id, ' ', p.nom) LIKE %?1%")
	public Page<Role> findAll(String keyword, Pageable pageable);
}
