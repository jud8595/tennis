package com.sc.tennis.graph.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sc.tennis.graph.Match;
import com.sc.tennis.graph.Score;
import com.sc.tennis.joueur.Joueur;

public class MatchImpl implements Match {
	
	private Map<Joueur, List<Integer>> m = new HashMap<Joueur, List<Integer>>(3);
	private Map<Joueur, Integer> setCount = new HashMap<Joueur, Integer>(3);
	private Score score;
	private Joueur joueur1;
	private Joueur joueur2;

	public MatchImpl(Score score, Joueur joueur1, Joueur joueur2) {
		this.score = score;
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		m.put(joueur1, new ArrayList<Integer>(5));
		m.put(joueur2, new ArrayList<Integer>(5));
		reset();
	}
	
	public void increment(Joueur joueur) {
		int j1 = Integer.parseInt(this.score.getSet().getScore(this.joueur1));
		int j2 = Integer.parseInt(this.score.getSet().getScore(this.joueur2));
		m.get(this.joueur1).add(j1);
		m.get(this.joueur2).add(j2);
		setCount.put(joueur, setCount.get(joueur)+1);
		
		if (setCount.get(this.joueur1) == 3 || setCount.get(this.joueur2) == 3) {
			this.score.setState(EndImpl.getInstance());
		} else {
			this.score.setState(this.score.getGame());
		}
	}

	public String getScore(Joueur joueur) {
		return Integer.toString(m.get(joueur).size());
	}
	
	public List<String> getSets(Joueur joueur) {
		List<String> result = new ArrayList<String>();
		for (Integer i : m.get(joueur)) {
			result.add(Integer.toString(i));
		}
		return result;
	}
	
	public void reset() {
		m.get(this.joueur1).clear();
		m.get(this.joueur2).clear();
		setCount.put(this.joueur1, 0);
		setCount.put(this.joueur2, 0);
	}
	
	@Override
	public String toString() {
		List<Integer> l1 = m.get(this.joueur1);
		List<Integer> l2 = m.get(this.joueur2);
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<l1.size(); i++) {
			sb.append(l1.get(i));
			sb.append("/");
			sb.append(l2.get(i));
			sb.append(" ");
		}
		return sb.substring(0, sb.length()-1);
	}

}
