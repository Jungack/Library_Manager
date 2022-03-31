package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.service.LivreServiceImpl;

@WebServlet("/livre_add")
public class LivreAddServlet extends HttpServlet {
	LivreServiceImpl livreService = LivreServiceImpl.getInstance();
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/livre_add.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (request.getParameter("titre") == null || request.getParameter("auteur") == null || request.getParameter("isbn") == null) {
				throw new ServletException();
			} else {
				int id = livreService.create(request.getParameter("titre"),
						request.getParameter("auteur"), request.getParameter("isbn"));
				response.sendRedirect(request.getContextPath() + "/livre_details?id=" + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        
	}

}
