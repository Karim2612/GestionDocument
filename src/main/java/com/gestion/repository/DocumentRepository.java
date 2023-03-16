package com.gestion.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestion.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer>{

	@Query("SELECT p FROM Document p WHERE CONCAT(p.id, ' ', p.nom, ' ', p.derniere_date_modification, ' ', p.taille_document, ' ', p.version, ' ', p.document_prive, ' ', p.approuve_par) LIKE %?1%") //p.approuve_par, document_type_id) LIKE %?1%")
	public Page<Document> findAll(String keyword, Pageable pageable);
}
