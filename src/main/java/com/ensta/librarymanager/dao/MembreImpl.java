package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class MembreImpl implements MembreDao {

	public static MembreImpl instance;

	private MembreImpl() {
	}

	public static MembreImpl getInstance() {
		if (instance == null) {
			instance = new MembreImpl();
		}
		return instance;
	}

	@Override
	public List<Membre> getList() throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT id, nom, prenom, adresse, email, telephone, abonnement\r\n"
							+ "FROM membre\r\n" + "ORDER BY nom, prenom");
			ResultSet rs = pstmt.executeQuery();
			List<Membre> listeMembre = new ArrayList<Membre>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String adresse = rs.getString("adresse");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				if (rs.getString("abonnement") != null) {
					Abonnement abonnement = Abonnement.valueOf(rs.getString("abonnement"));
					listeMembre.add(new Membre(id, nom, prenom, adresse, email, telephone, abonnement));
				} else {
					listeMembre.add(new Membre(id, nom, prenom, adresse, email, telephone, null));
				}
			}
			return listeMembre;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public Membre getById(int id) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT nom, prenom, adresse, email, telephone, abonnement\r\n"
							+ "FROM membre\r\n" + "WHERE id = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String adresse = rs.getString("adresse");
			String email = rs.getString("email");
			String telephone = rs.getString("telephone");
			if (rs.getString("abonnement") != null) {
				Abonnement abonnement = Abonnement.valueOf(rs.getString("abonnement"));
				Membre membre = new Membre(id, nom, prenom, adresse, email, telephone, abonnement);
				return membre;
			} else {
				Membre membre = new Membre(id, nom, prenom, adresse, email, telephone, null);
				return membre;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement)\r\n" + "VALUES (?, ?, ?, ?, ?, NULL)",
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, nom);
			pstmt.setString(2, prenom);
			pstmt.setString(3, adresse);
			pstmt.setString(4, email);
			pstmt.setString(5, telephone);
			pstmt.executeUpdate();
			ResultSet resultSet = pstmt.getGeneratedKeys();
			resultSet.next();
			int id = resultSet.getInt(1);
			return id;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public void update(Membre membre) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE membre\r\n" + "SET nom = ?, prenom = ?, adresse = ?, email = ?, telephone = ?,\r\n"
							+ "abonnement = ?\r\n" + "WHERE id = ?;");
			pstmt.setString(1, membre.getNom());
			pstmt.setString(2, membre.getPrenom());
			pstmt.setString(3, membre.getAdresse());
			pstmt.setString(4, membre.getEmail());
			pstmt.setString(5, membre.getTelephone());
			pstmt.setString(6, membre.getAbonnement().name());
			pstmt.setInt(7, membre.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public void delete(int id) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM membre WHERE id = ?");
			pstmt.setInt(1, id);
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
			PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(id) AS count FROM membre");
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
