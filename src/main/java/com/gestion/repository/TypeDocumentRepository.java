package com.gestion.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.gestion.model.TypeDocument;

public interface TypeDocumentRepository extends JpaRepository<TypeDocument, Integer>{

	@Query("SELECT p FROM TypeDocument p WHERE CONCAT(p.id, ' ', p.nom, ' ', p.suffixe, ' ', p.description) LIKE %?1%") //p.approuve_par, document_type_id) LIKE %?1%")
	public Page<TypeDocument> findAll(String keyword, Pageable pageable);
}
