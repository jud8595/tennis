package com.sc.tennis.joueur.impl;

import com.sc.tennis.joueur.Joueur;

public class JoueurImpl implements Joueur {

	private String name;
	
	public JoueurImpl(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
