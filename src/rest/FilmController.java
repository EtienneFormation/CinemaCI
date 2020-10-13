package rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bll.FilmManager;
import bo.Film;

@RestController
@RequestMapping("/rest/films")
public class FilmController {
	private FilmManager fm;
	
	public FilmController() {
		ApplicationContext context = new ClassPathXmlApplicationContext("springContext.xml");
		fm = context.getBean("fm", FilmManager.class);
	}
	
	@GetMapping
	public List<SimpleFilm> all() {
		List<Film> films = fm.selectAll();
		List<SimpleFilm> result = new ArrayList<>();
		for (Film current : films) result.add(new SimpleFilm(current));
		return result;
	}
	
	@GetMapping(value="/{id}")
	public SimpleFilm select(@PathVariable(value = "") int id) {
		return new SimpleFilm(fm.select(id));
	}
	
	@PostMapping
	public SimpleFilm add(@RequestBody SimpleFilm film) {
		Film newFilm = fm.insert(film.getNom(), film.getDuree(), film.getDescription());
		return new SimpleFilm(newFilm);
	}
	
	@PutMapping(value="/{id}")
	public void update(@RequestBody SimpleFilm film, @PathVariable int id) {
		Film filmAModifier = fm.select(id);
		filmAModifier.setNom(film.getNom());
		filmAModifier.setDuree(film.getDuree());
		filmAModifier.setDescription(film.getDescription());
		fm.update(filmAModifier);
	}
	
	@DeleteMapping(value="/{id}")
	public void delete(@PathVariable int id) {
		fm.delete(id);
	}
}
