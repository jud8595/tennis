package com.sc.tennis.graph.impl;

import java.util.HashMap;
import java.util.Map;

import com.sc.tennis.graph.Score;
import com.sc.tennis.graph.State;
import com.sc.tennis.joueur.Joueur;

public class DeuceImpl implements State {
	
	private Map<Joueur, Integer> m = new HashMap<Joueur, Integer>(3);
	private Score score;
	private Joueur joueur1;
	private Joueur joueur2;

	public DeuceImpl(Score score, Joueur joueur1, Joueur joueur2) {
		this.score = score;
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		reset();
	}
	
	public void increment(Joueur joueur) {
		m.put(joueur, m.get(joueur)+1);
		int j1 = m.get(this.joueur1);
		int j2 = m.get(this.joueur2);
		if (Math.abs(j1 - j2) == 2) {
			this.score.setState(this.score.getSet());
			this.score.increment(joueur);
			reset();
		}	
	}
	
	public void reset() {
		m.put(joueur1, 0);
		m.put(joueur2, 0);
	}
	
	public boolean isDeuce() {
		return m.get(joueur1) == m.get(joueur2);
	}
	
	public boolean isAdvantage(Joueur joueur) {
		int j1 = m.get(joueur1);
		int j2 = m.get(joueur2);
		return (j1 != j2) && m.get(joueur) == Math.max(j1, j2); 
	}
	
	public String getScore(Joueur joueur) {
		return "";
	}

	@Override
	public String toString() {
		int j1 = m.get(joueur1);
		int j2 = m.get(joueur2);
		if (j1 == j2) {
			return "Deuce";
		} else if (j1 > j2) {
			return "advantage " + joueur1.getName();
		} else {
			return "advantage " + joueur1.getName();
		}
	}
}
