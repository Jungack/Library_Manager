package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.EmpruntServiceImpl;
import com.ensta.librarymanager.service.MembreServiceImpl;

@WebServlet("/membre_details")
public class MembreDetailsServlet extends HttpServlet {
	MembreServiceImpl membreService = MembreServiceImpl.getInstance();
	EmpruntServiceImpl empruntService = EmpruntServiceImpl.getInstance();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("membre", membreService.getById(Integer.parseInt(request.getParameter("id"))));
			request.setAttribute("emprunts",
					this.empruntService.getListCurrentByMembre(Integer.parseInt(request.getParameter("id"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/membre_details.jsp").forward(request, response);
	}

	/**
	 *
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (request.getParameter("nom") == null || request.getParameter("prenom") == null
					|| request.getParameter("adresse") == null || request.getParameter("email") == null
					|| request.getParameter("telephone") == null || request.getParameter("abonnement") == null) {
				throw new ServletException();
			} else {
				Membre membre = membreService.getById(Integer.parseInt(request.getParameter("id")));
				membre.setNom(request.getParameter("nom"));
				membre.setPrenom(request.getParameter("prenom"));
				membre.setAdresse(request.getParameter("adresse"));
				membre.setEmail(request.getParameter("email"));
				membre.setTelephone(request.getParameter("telephone"));
				membre.setAbonnement(Abonnement.valueOf(request.getParameter("abonnement")));
				membreService.update(membre);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/membre_details?id=" + request.getParameter("id"));
	}

}
