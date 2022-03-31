package com.ensta.librarymanager.service;

import java.time.LocalDate;
import java.util.List;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.dao.EmpruntImpl;
import com.ensta.librarymanager.modele.Membre;

public class EmpruntServiceImpl implements EmpruntService {

	public static EmpruntServiceImpl instance;

	private EmpruntServiceImpl() {
	}

	public static EmpruntServiceImpl getInstance() {
		if (instance == null) {
			instance = new EmpruntServiceImpl();
		}
		return instance;
	}

	@Override
	public List<Emprunt> getList() throws ServiceException {
		try {
			List<Emprunt> listeEmprunt = EmpruntImpl.getInstance().getList();
			return listeEmprunt;
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	@Override
	public List<Emprunt> getListCurrent() throws ServiceException {
		try {
			List<Emprunt> listeEmprunt = EmpruntImpl.getInstance().getListCurrent();
			return listeEmprunt;
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	@Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException {
		try {
			List<Emprunt> listeEmprunt = EmpruntImpl.getInstance().getListCurrentByMembre(idMembre);
			return listeEmprunt;
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException {
		try {
			List<Emprunt> listeEmprunt = EmpruntImpl.getInstance().getListCurrentByLivre(idLivre);
			return listeEmprunt;
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	@Override
	public Emprunt getById(int id) throws ServiceException {
		try {
			System.out.println("CACACAC  : " + id);
			Emprunt emprunt = EmpruntImpl.getInstance().getById(id);
			return emprunt;
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException {
		try {
			EmpruntImpl.getInstance().create(idMembre, idLivre, dateEmprunt);
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	@Override
	public void returnBook(int id) throws ServiceException {
		try {
			Emprunt emprunt = EmpruntImpl.getInstance().getById(id);
			emprunt.setDateRetour(LocalDate.now());
			EmpruntImpl.getInstance().update(emprunt);
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	@Override
	public int count() throws ServiceException {
		try {
			int res = EmpruntImpl.getInstance().count();
			return res;
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	@Override
	public boolean isLivreDispo(int idLivre) throws ServiceException {
		try {
			List<Emprunt> listeEmprunt = getListCurrent();
			for (Emprunt emprunt : listeEmprunt) {
				if (emprunt.getIdLivre() == idLivre) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	@Override
	public boolean isEmpruntPossible(Membre membre) throws ServiceException {
		try {
			return getListCurrentByMembre(membre.getId()).size() < membre.getAbonnement().getLimit();
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

}
