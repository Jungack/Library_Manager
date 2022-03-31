package com.ensta.librarymanager.service;

import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.LivreImpl;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;

public class LivreServiceImpl implements LivreService {

	public static LivreServiceImpl instance;

	private LivreServiceImpl() {
	}

	public static LivreServiceImpl getInstance() {
		if (instance == null) {
			instance = new LivreServiceImpl();
		}
		return instance;
	}

	@Override
	public List<Livre> getList() throws ServiceException {
		try {
			List<Livre> listeLivre = LivreImpl.getInstance().getList();
			return listeLivre;
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	@Override
	public List<Livre> getListDispo() throws ServiceException {
		try {
			List<Livre> listeLivre = getList();
			List<Livre> listeLivreDispo = new ArrayList<Livre>();
			for (Livre livre : listeLivre) {
				if (EmpruntServiceImpl.getInstance().isLivreDispo(livre.getId())) {
					listeLivreDispo.add(livre);
				}
			}
			return listeLivreDispo;
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	@Override
	public Livre getById(int id) throws ServiceException {
		try {
			Livre livre = LivreImpl.getInstance().getById(id);
			return livre;
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	@Override
	public int create(String titre, String auteur, String isbn) throws ServiceException {
		try {
			int res = LivreImpl.getInstance().create(titre, auteur, isbn);
			return res;
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	@Override
	public void update(Livre livre) throws ServiceException {
		try {
			LivreImpl.getInstance().update(livre);
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	@Override
	public void delete(int id) throws ServiceException {
		try {
			LivreImpl.getInstance().delete(id);
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	@Override
	public int count() throws ServiceException {
		try {
			return LivreImpl.getInstance().count();
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

}
