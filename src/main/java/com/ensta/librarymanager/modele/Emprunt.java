package com.ensta.librarymanager.modele;

import java.time.LocalDate;

public class Emprunt {

	private int id;
	private int idMembre;
	private int idLivre;
	private LocalDate dateEmprunt;
	private LocalDate dateRetour;

	public Emprunt(int id2, int idMembre2, int idLivre2, LocalDate dateEmprunt2, LocalDate dateRetour2) {
		id = id2;
		idMembre = idMembre2;
		idLivre = idLivre2;
		dateEmprunt = dateEmprunt2;
		dateRetour = dateRetour2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdMembre() {
		return idMembre;
	}

	public void setIdMembre(int idMembre) {
		this.idMembre = idMembre;
	}

	public int getIdLivre() {
		return idLivre;
	}

	public void setIdLivre(int idLivre) {
		this.idLivre = idLivre;
	}

	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public LocalDate getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(LocalDate dateRetour) {
		this.dateRetour = dateRetour;
	}
}
