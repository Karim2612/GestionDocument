package com.gestion.model;

import java.sql.Date;
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
import javax.persistence.ManyToOne;

@Entity
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, columnDefinition = "nvarchar(20)")
	private String sujet;

	@Column(nullable = false)
	private String texte;

	@Column(nullable = false)
	private Date deniereModification;
	
	@ManyToOne
	@JoinColumn(name = "utilisateur_id")
	private Utilisateur utilisateur;

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	@ManyToOne
	@JoinColumn(name = "document_id")
	private Document document;

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	

public Note() {}

public Note(Integer id, String sujet, String texte, Date deniereModification,Utilisateur utilisateur,Document document) {  
super();
this.id = id;
this.sujet = sujet;
this.texte = texte;
this.deniereModification = deniereModification;
this.utilisateur = utilisateur;
this.document = document;
}

	public Note(Integer id) {
		super();
		this.id = id;
	}

	public Note(String sujet) {
		super();
		this.sujet = sujet;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public Date getDeniereModification() {
		return deniereModification;
	}

	public void setDeniereModification(Date deniereModification) {
		this.deniereModification = deniereModification;
	}

	@Override
	public String toString() {
		return "Utilisateur " +
				"[id=" + id +
				", sujet=" + sujet +
				", texte=" + texte +
				", deniereModification=" + deniereModification +
				"]";
	}
}
