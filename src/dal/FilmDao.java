package dal;

import java.util.List;

import bo.Film;

public interface FilmDao {

	List<Film> selectAll();

	Film selectById(int idFilm);

	void delete(Film film);

	void update(Film filmAModifier);

	Film insert(Film filmAAjouter);

}
