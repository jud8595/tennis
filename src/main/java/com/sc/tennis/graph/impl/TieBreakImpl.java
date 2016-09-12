package com.sc.tennis.graph.impl;

import java.util.HashMap;
import java.util.Map;

import com.sc.tennis.graph.Score;
import com.sc.tennis.graph.State;
import com.sc.tennis.joueur.Joueur;

public class TieBreakImpl implements State {
	
	private Map<Joueur, Integer> m = new HashMap<Joueur, Integer>(3);
	private Score score;
	private Joueur joueur1;
	private Joueur joueur2;

	public TieBreakImpl(Score score, Joueur joueur1, Joueur joueur2) {
		this.score = score;
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		reset();
	}
	
	public void increment(Joueur joueur) {
		int newscore = m.get(joueur)+1;
		m.put(joueur, newscore);
		if (newscore == 7) {
			this.score.setState(this.score.getSet());
			this.score.increment(joueur);
			reset();
		}
	}

	public void reset() {
		m.put(this.joueur1, 0);
		m.put(this.joueur2, 0);
	}
	
	public String getScore(Joueur joueur) {
		return Integer.toString(m.get(joueur));
	}
	
	@Override
	public String toString() {
		return m.get(this.joueur1) + "-" + m.get(this.joueur2);
	}
}
