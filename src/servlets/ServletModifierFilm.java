package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bll.FilmManager;
import bo.Film;

/**
 * Servlet implementation class ServletModifierFilm
 */
@WebServlet("/modifierFilm")
public class ServletModifierFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModifierFilm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("springContext.xml");
		FilmManager fm = context.getBean("fm", FilmManager.class);
		
		int idFilm = Integer.valueOf(request.getParameter("idFilm"));
		Film filmSelectionne = fm.select(idFilm); 
		
		request.setAttribute("film", filmSelectionne);
		
		this.getServletContext().getNamedDispatcher("modifierFilm").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("springContext.xml");
		FilmManager fm = context.getBean("fm", FilmManager.class);
		
		int idFilm = Integer.valueOf(request.getParameter("idFilm"));
		String nouveauNom = request.getParameter("nouveauNom");
		int nouvelleDuree = Integer.valueOf(request.getParameter("nouvelleDuree"));
		String nouvelleDescription = request.getParameter("nouvelleDescription");
		
		Film filmAModifier = fm.select(idFilm);
		filmAModifier.setNom(nouveauNom);
		filmAModifier.setDuree(nouvelleDuree);
		filmAModifier.setDescription(nouvelleDescription);
		
		fm.update(filmAModifier);
		
		String path = request.getContextPath() + "/accueil";
		response.sendRedirect(path);
	}

}
