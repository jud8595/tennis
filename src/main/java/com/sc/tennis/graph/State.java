package com.sc.tennis.graph;

import com.sc.tennis.joueur.Joueur;

public interface State {

	public void increment(Joueur joueur);
	
	public String getScore(Joueur joueur);
	
	public void reset();
}
