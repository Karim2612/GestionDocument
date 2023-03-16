package com.gestion.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestion.model.Note;

public interface NoteRepository extends JpaRepository<Note, Integer>{

	@Query("SELECT p FROM Note p WHERE CONCAT(p.id, ' ', p.sujet) LIKE %?1%")
	//@Query("SELECT p,p2 FROM Note p,p2 WHERE CONCAT(p.id, ' ', p.sujet) LIKE %?1%")
	public Page<Note> findAll(String keyword, Pageable pageable);
}
