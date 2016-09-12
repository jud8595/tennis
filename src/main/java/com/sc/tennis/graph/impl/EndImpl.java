package com.sc.tennis.graph.impl;

import com.sc.tennis.graph.State;
import com.sc.tennis.joueur.Joueur;

public class EndImpl implements State {
	
	private static State end = new EndImpl();

	public void increment(Joueur joueur) {
		throw new IllegalStateException("Game is over");
	}
	
	public void reset() {
		throw new IllegalStateException("cannot reset");
	}
	
	public String getScore(Joueur joueur) {
		throw new IllegalStateException("cannot get score");
	}
	
	public static State getInstance() {
		return EndImpl.end;
	}
}
