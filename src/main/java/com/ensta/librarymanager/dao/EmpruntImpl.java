package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class EmpruntImpl implements EmpruntDao {

	public static EmpruntImpl instance;

	private EmpruntImpl() {
	}

	public static EmpruntImpl getInstance() {
		if (instance == null) {
			instance = new EmpruntImpl();
		}
		return instance;
	}

	@Override
	public List<Emprunt> getList() throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT id, idMembre, idLivre, dateEmprunt, dateRetour FROM Emprunt");
			ResultSet rs = pstmt.executeQuery();
			List<Emprunt> listeEmprunt = new ArrayList<Emprunt>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int idMembre = rs.getInt("idMembre");
				int idLivre = rs.getInt("idLivre");
				LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
				if (rs.getDate("dateRetour") != null) {
					LocalDate dateRetour = rs.getDate("dateRetour").toLocalDate();
					listeEmprunt.add(new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour));
				} else {
					listeEmprunt.add(new Emprunt(id, idMembre, idLivre, dateEmprunt, null));
				}
			}
			return listeEmprunt;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public List<Emprunt> getListCurrent() throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT id, idMembre, idLivre, dateEmprunt, dateRetour FROM Emprunt WHERE dateRetour IS NULL");
			ResultSet rs = pstmt.executeQuery();
			List<Emprunt> listeEmprunt = new ArrayList<Emprunt>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int idMembre = rs.getInt("idMembre");
				int idLivre = rs.getInt("idLivre");
				LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
				listeEmprunt.add(new Emprunt(id, idMembre, idLivre, dateEmprunt, null));
			}
			return listeEmprunt;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT id, idLivre, dateEmprunt FROM Emprunt WHERE dateRetour IS NULL AND idMembre = ?");
			pstmt.setInt(1, idMembre);
			ResultSet rs = pstmt.executeQuery();
			List<Emprunt> listeEmprunt = new ArrayList<Emprunt>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int idLivre = rs.getInt("idLivre");
				LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
				listeEmprunt.add(new Emprunt(id, idMembre, idLivre, dateEmprunt, null));
			}
			return listeEmprunt;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT id, idMembre, dateEmprunt, dateRetour FROM Emprunt WHERE idLivre = ?");
			pstmt.setInt(1, idLivre);
			ResultSet rs = pstmt.executeQuery();
			List<Emprunt> listeEmprunt = new ArrayList<Emprunt>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int idMembre = rs.getInt("idMembre");
				LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
				LocalDate dateRetour = rs.getDate("dateRetour").toLocalDate();
				listeEmprunt.add(new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour));
			}
			return listeEmprunt;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public Emprunt getById(int id) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT idMembre, idLivre, dateEmprunt, dateRetour FROM Emprunt WHERE id = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int idMembre = rs.getInt("idMembre");
			int idLivre = rs.getInt("idLivre");
			LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
			if (rs.getDate("dateRetour") != null) {
				LocalDate dateRetour = rs.getDate("dateRetour").toLocalDate();
				Emprunt emprunt = new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour);			
				return emprunt;
			} else {
				Emprunt emprunt = new Emprunt(id, idMembre, idLivre, dateEmprunt, null);			
				return emprunt;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour) VALUES (?, ?, ?, ?)");
			pstmt.setInt(1, idMembre);
			pstmt.setInt(2, idLivre);
			pstmt.setDate(3, java.sql.Date.valueOf(dateEmprunt));
			pstmt.setDate(4, null);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public void update(Emprunt emprunt) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("UPDATE emprunt\r\n"
					+ "SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ?\r\n" + "WHERE id = ?");
			pstmt.setInt(1, emprunt.getIdMembre());
			pstmt.setInt(2, emprunt.getIdLivre());
			pstmt.setDate(3, java.sql.Date.valueOf(emprunt.getDateEmprunt()));
			pstmt.setDate(4, java.sql.Date.valueOf(emprunt.getDateRetour()));
			pstmt.setInt(5, emprunt.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public int count() throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(id) AS count FROM emprunt");
			pstmt.executeQuery();
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int res = rs.getInt("count");
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

}
