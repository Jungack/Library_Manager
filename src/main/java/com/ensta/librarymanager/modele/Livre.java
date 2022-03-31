package com.ensta.librarymanager.modele;

public class Livre {

	private int id;
	private String titre;
	private String auteur;
	private String isbn;

	public Livre(int id2, String titre2, String auteur2, String isbn2) {
		id = id2;
		titre = titre2;
		auteur = auteur2;
		isbn = isbn2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
