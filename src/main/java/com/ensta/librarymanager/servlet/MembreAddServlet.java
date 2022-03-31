package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.service.MembreServiceImpl;

@WebServlet("/membre_add")
public class MembreAddServlet extends HttpServlet {
	MembreServiceImpl membreService = MembreServiceImpl.getInstance();
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/membre_add.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (request.getParameter("nom") == null || request.getParameter("prenom") == null
					|| request.getParameter("adresse") == null || request.getParameter("email") == null
					|| request.getParameter("telephone") == null) {
				throw new ServletException();
			} else {
				int id = membreService.create(request.getParameter("nom"), request.getParameter("prenom"),
						request.getParameter("adresse"), request.getParameter("email"),
						request.getParameter("telephone"));
				response.sendRedirect(request.getContextPath() + "/membre_details?id=" + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
