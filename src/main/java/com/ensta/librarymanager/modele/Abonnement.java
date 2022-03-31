package com.ensta.librarymanager.modele;

public enum Abonnement {
	BASIC(2), PREMIUM(5), VIP(20);
	
	private int limit;
	
	private Abonnement(int limit2) {
		limit = limit2;
	}
	
	public int getLimit() {
		return limit;
	}
}
