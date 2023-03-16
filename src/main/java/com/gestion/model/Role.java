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

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, columnDefinition = "nvarchar(20)")
	private String description;

	@Column(nullable = false)
	private String nom;

	@Column(nullable = false)
	private String rolecol;

	@ManyToMany
	@JoinTable(name = "role_has_utilisateur", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "utilisateur_id"))
	private Set<Utilisateur> utilisateurs = new HashSet<Utilisateur>();

public Role() {}

	public Role(Integer id) {
		super();
		this.id = id;
	}

	public Role(String nom) {
		super();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRolecol() {
		return rolecol;
	}

	public void setRolecol(String rolecol) {
		this.rolecol = rolecol;
	}

	public Set<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public void addUtilisateur(Utilisateur utilisateur) {
		utilisateurs.add(utilisateur);
	}
	
	public void removeUtilisateur(Utilisateur utilisateur) {
		utilisateurs.remove(utilisateur);
	}
	@Override
	public String toString() {
		return "Utilisateur " +
				"[id=" + id +
				", nom=" + nom +
				", description=" + description +
				", rolecol=" + rolecol +
				"]";
	}
}
