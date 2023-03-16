package com.gestion.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestion.model.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Integer>{

	@Query("SELECT p FROM Categorie p WHERE CONCAT(p.id, ' ', p.nom) LIKE %?1%")
	public Page<Categorie> findAll(String keyword, Pageable pageable);
}
