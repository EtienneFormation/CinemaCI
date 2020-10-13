package bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="seances")
public class Seance {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="salle")
	private int salle;
	
	@Column(name="heure")
	private int heure;
	
	@ManyToOne
	@JoinColumn(name="id_film", nullable=false)
	private Film film;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSalle() {
		return salle;
	}

	public void setSalle(int salle) {
		this.salle = salle;
	}

	public int getHeure() {
		return heure;
	}

	public void setHeure(int heure) {
		this.heure = heure;
	}
	
	public Film getFilm() {
		return film;
	}
	
	public void setFilm(Film film) {
		this.film = film;
	}
}
