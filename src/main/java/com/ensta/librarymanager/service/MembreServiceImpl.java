package com.ensta.librarymanager.service;

import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.MembreImpl;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Membre;

public class MembreServiceImpl implements MembreService {

	public static MembreServiceImpl instance;

	private MembreServiceImpl() {
	}

	public static MembreServiceImpl getInstance() {
		if (instance == null) {
			instance = new MembreServiceImpl();
		}
		return instance;
	}

	@Override
	public List<Membre> getList() throws ServiceException {
		try {
			List<Membre> listeMembre = MembreImpl.getInstance().getList();
			return listeMembre;
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	@Override
	public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
		try {
			List<Membre> listeMembreEmpruntPossible = new ArrayList<Membre>();
			for (Membre membre : this.getList()) {
				if (EmpruntServiceImpl.getInstance().isEmpruntPossible(membre))
					listeMembreEmpruntPossible.add(membre);
			}
			return listeMembreEmpruntPossible;
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	@Override
	public Membre getById(int id) throws ServiceException {
		try {
			return MembreImpl.getInstance().getById(id);
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	@Override
	public int create(String nom, String prenom, String adresse, String email, String telephone)
			throws ServiceException {
		try {
			return MembreImpl.getInstance().create(nom, prenom, adresse, email, telephone);
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	@Override
	public void update(Membre membre) throws ServiceException {
		try {
			MembreImpl.getInstance().update(membre);
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	@Override
	public void delete(int id) throws ServiceException {
		try {
			MembreImpl.getInstance().delete(id);
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	@Override
	public int count() throws ServiceException {
		try {
			return MembreImpl.getInstance().count();
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

}
