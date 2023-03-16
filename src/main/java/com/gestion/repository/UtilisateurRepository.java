package com.gestion.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestion.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>{

	@Query("SELECT p FROM Utilisateur p WHERE CONCAT(p.id, ' ', p.nomUtilisateur) LIKE %?1%")
	public Page<Utilisateur> findAll(String keyword, Pageable pageable);
}
