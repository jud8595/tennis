package com.sc.tennis.graph.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.sc.tennis.graph.Score;
import com.sc.tennis.graph.State;
import com.sc.tennis.joueur.Joueur;

public class GameImpl implements State {
	
	enum POINT {love, fifteen, thirty, forty}
	
	private String[] toPoint = new String[] {"0", "15", "30", "40"};
	
	private Map<Joueur, Integer> m = new HashMap<Joueur, Integer>(3);
	private Score score;
	private Joueur joueur1;
	private Joueur joueur2;

	public GameImpl(Score score, Joueur joueur1, Joueur joueur2) {
		this.score = score;
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		reset();
	}
	
	public void increment(Joueur joueur) {
		int newscore = m.get(joueur)+1;
		m.put(joueur, newscore);
		if (newscore == 4) {
			this.score.setState(this.score.getSet());
			this.score.increment(joueur);
			reset();
		} else if (this.isDeuce()) {
			this.score.setState(this.score.getDeuce());
			reset();
		}
	}
	
	public void reset() {
		m.put(this.joueur1, 0);
		m.put(this.joueur2, 0);
	}
	
	private boolean isDeuce() {
		for (Entry<Joueur, Integer> e : m.entrySet()) {
			if (e.getValue() != 3) return false;
		}
		return true;
	}
	
	public String getScore(Joueur joueur) {
		return toPoint[m.get(joueur)];
	}
	
	@Override
	public String toString() {
		return getScore(this.joueur1) + "-" + getScore(this.joueur2);
	}

}
