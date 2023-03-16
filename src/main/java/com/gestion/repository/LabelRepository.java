package com.gestion.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestion.model.Label;

public interface LabelRepository extends JpaRepository<Label, Integer>{

	@Query("SELECT p FROM Label p WHERE CONCAT(p.id, ' ', p.nom) LIKE %?1%")
	public Page<Label> findAll(String keyword, Pageable pageable);
}
