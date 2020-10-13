package dal;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;

import bo.Film;

public class FilmDaoHibernateImpl implements FilmDao {
	private Session session;
	
	public FilmDaoHibernateImpl() {
		session = SessionSingleton.getInstance();
	}
	
	
	@Override
	public List<Film> selectAll() {
		return session.createQuery("from Film", Film.class).list();
	}


	@Override
	public Film selectById(int idFilm) {
		return session.load(Film.class, idFilm);
	}


	@Override
	public void delete(Film film) {
		Transaction t = session.beginTransaction();
		session.delete(film);
		t.commit();
	}


	@Override
	public void update(Film filmAModifier) {
		Transaction t = session.beginTransaction();
		session.update(filmAModifier);
		t.commit();
	}


	@Override
	public Film insert(Film filmAAjouter) {
		Transaction t = session.beginTransaction();
		session.save(filmAAjouter);
		t.commit();
		return filmAAjouter;
	}

}
