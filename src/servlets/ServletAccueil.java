package servlets;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/accueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAccueil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("springContext.xml");
		FilmManager fm = context.getBean("fm", FilmManager.class);
		
		List<Film> films = fm.selectAll();
		
		request.setAttribute("films", films);
		this.getServletContext().getNamedDispatcher("accueil").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("springContext.xml");
		FilmManager fm = context.getBean("fm", FilmManager.class);
		
		String nouveauNom = request.getParameter("nouveauNom");
		int nouvelleDuree = Integer.valueOf(request.getParameter("nouvelleDuree"));
		String nouvelleDescription = request.getParameter("nouvelleDescription");
		
		fm.insert(nouveauNom, nouvelleDuree, nouvelleDescription);
		doGet(request, response);
	}

}
