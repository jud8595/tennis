package com.sc.tennis.graph.impl;

import java.util.HashMap;
import java.util.Map;

import com.sc.tennis.graph.Score;
import com.sc.tennis.graph.State;
import com.sc.tennis.joueur.Joueur;

public class SetImpl implements State {
	
	private Map<Joueur, Integer> m = new HashMap<Joueur, Integer>(3);
	private Score score;
	private Joueur joueur1;
	private Joueur joueur2;

	public SetImpl(Score score, Joueur joueur1, Joueur joueur2) {
		this.score = score;
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		reset();
	}
	
	public void increment(Joueur joueur) {
		int newscore = m.get(joueur)+1;
		m.put(joueur, newscore);

		int j1 = m.get(joueur1);
		int j2 = m.get(joueur2);
		if (j1 == 6 && j2 == 6) {
			this.score.setState(this.score.getTieBreak());
		} else if (newscore == 6 && Math.abs(j1-j2) >= 2 || newscore == 7) {
			this.score.setState(this.score.getMatch());
			this.score.increment(joueur);
			reset();
		} else {
			this.score.setState(this.score.getGame());
		}
	}
	
	public String getScore(Joueur joueur) {
		return Integer.toString(m.get(joueur));
	}
	
	public void reset() {
		m.put(this.joueur1, 0);
		m.put(this.joueur2, 0);
	}
	
	@Override
	public String toString() {
		return m.get(joueur1) + "/" +  m.get(joueur2);
	}
}
