package bll;

import java.util.List;

import bo.Film;
import dal.FilmDao;

public class FilmManager {
	private FilmDao dao;

	public FilmDao getDao() {
		return dao;
	}

	public void setDao(FilmDao dao) {
		this.dao = dao;
	}

	public List<Film> selectAll() {
		return dao.selectAll();
	}

	public void delete(int idFilm) {
		Film film = select(idFilm);
		dao.delete(film);
	}

	public Film select(int idFilm) {
		return dao.selectById(idFilm);
	}

	public void update(Film filmAModifier) {
		dao.update(filmAModifier);
	}

	public Film insert(String nouveauNom, int nouvelleDuree, String nouvelleDescription) {
		Film filmAAjouter = new Film();
		filmAAjouter.setNom(nouveauNom);
		filmAAjouter.setDuree(nouvelleDuree);
		filmAAjouter.setDescription(nouvelleDescription);
		return dao.insert(filmAAjouter);
	}
	
	
}
