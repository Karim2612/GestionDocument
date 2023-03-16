package com.gestion.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.*;

@Entity
public class TypeDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, columnDefinition = "nvarchar(45)")
	private String nom;

	@Column(nullable = false, columnDefinition = "nvarchar(45)")
	private String suffixe;
	
	@Column(nullable = false, columnDefinition = "nvarchar(200)")
	private String description;
	
	//@ManyToOne
	//@JoinColumn(name = "type_document_id")
	//private Type_document type_document;

	
public TypeDocument() {}
	
	public TypeDocument(Integer id, String nom, String suffixe, String description) {  
		super();
		this.id = id;
		this.nom = nom;
		this.suffixe = suffixe;
		this.description = description;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getSuffixe() {
		return suffixe;
	}

	public void setSuffixe(String suffixe) {
		this.suffixe = suffixe;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	@Override
	public String toString() {
		return "TypeDocument " +
				"[id=" + id +
				", nom=" + nom +
				", suffixe=" + suffixe +
				", description=" + description +
				"]";
	}
}
