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

/**
 * Servlet implementation class SupprimerFilm
 */
@WebServlet("/supprimerFilm")
public class ServletSupprimerFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSupprimerFilm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("springContext.xml");
		FilmManager fm = context.getBean("fm", FilmManager.class);
		
		int idFilm = Integer.valueOf(request.getParameter("idFilm"));
		fm.delete(idFilm);
		
		String path = request.getContextPath() + "/accueil";
		response.sendRedirect(path);
	}

}
