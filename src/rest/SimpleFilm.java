package rest;

import bo.Film;

public class SimpleFilm {
	private int id;
	private String nom;
	private String description;
	private int duree;
	
	public SimpleFilm() {}
	
	public SimpleFilm(Film film) {
		this.id = film.getId();
		this.nom = film.getNom();
		this.description = film.getDescription();
		this.duree = film.getDuree();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	
	public Film toFilm() {
		Film film = new Film();
		film.setNom(this.nom);
		film.setDescription(this.description);
		film.setDuree(this.duree);
		return film;
	}
}
