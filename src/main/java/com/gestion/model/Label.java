package com.gestion.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Label {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, columnDefinition = "nvarchar(45)")
	private String nom;
	
	@ManyToMany
	@JoinTable(name = "Label_has_Document", joinColumns = @JoinColumn(name = "label_id"), inverseJoinColumns = @JoinColumn(name = "document_id"))
	private Set<Document> documents = new HashSet<Document>();


public Label() {}

	public Label(Integer id, String nom) {  
		super();
		this.id = id;
		this.nom = nom;
	
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
	
	
	public Set<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	public void addDocument(Document document) {
		documents.add(document);
	}
	
	public void removeDocument(Document document) {
		documents.remove(document);
	}


	@Override
	public String toString() {
		return "Document " +
				"[id=" + id +
				", nom=" + nom +
				"]";
	}
}
