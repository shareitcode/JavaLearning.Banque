package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.entites.Compte;
import metier.sessions.BanqueLocal;

@WebServlet("/comptes")
public final class ComptesServlet extends HttpServlet {

	@EJB
	BanqueLocal banque;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		Compte[] comptes = banque.consulterComptes();
		if (comptes.length > 0)
			req.setAttribute("comptes", comptes);
		
		getServletContext().getRequestDispatcher("/WEB-INF/Comptes.jsp").forward(req, resp);
	}
	
}
