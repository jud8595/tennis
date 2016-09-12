package com.sc.tennis.graph.impl;

import java.util.List;

import com.sc.tennis.graph.Score;
import com.sc.tennis.graph.State;
import com.sc.tennis.joueur.Joueur;

public class ScoreImpl implements Score {

	private Joueur joueur1;
	private Joueur joueur2;
	private State state;
	private GameImpl game;
	private DeuceImpl deuce;
	private SetImpl set;
	private TieBreakImpl tieBreak;
	private MatchImpl match;
	
	public ScoreImpl(Joueur joueur1, Joueur joueur2) {
		this.game = new GameImpl(this, joueur1, joueur2);
		this.deuce = new DeuceImpl(this, joueur1, joueur2);
		this.set = new SetImpl(this, joueur1, joueur2);
		this.tieBreak = new TieBreakImpl(this, joueur1, joueur2);
		this.match = new MatchImpl(this, joueur1, joueur2);
		this.state = this.game;
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
	}
	
	public void increment(Joueur joueur) {
		state.increment(joueur);
	}
	
	public boolean isGameOver() {
		return (state instanceof EndImpl);
	}

	public String getCurrentGame(Joueur joueur) {
		return this.game.getScore(joueur);
	}
	
	public boolean isPlayingTieBreak() {
		return (state instanceof TieBreakImpl);
	}
	
	public boolean isPlayingDeuce() {
		return (state instanceof DeuceImpl);
	}
	
	public boolean isDeuce() {
		if (!isPlayingDeuce()) {
			throw new IllegalStateException("not playing deuce");
		}
		return this.deuce.isDeuce();
	}
	
	public boolean isAdvantage(Joueur joueur) {
		if (!isPlayingDeuce()) {
			throw new IllegalStateException("not playing deuce");
		}
		return this.deuce.isAdvantage(joueur);
	}
	
	public String getCurrentSet(Joueur joueur) {
		return this.set.getScore(joueur);
	}
	
	public String getCurrentTieBreak(Joueur joueur) {
		return this.tieBreak.getScore(joueur);
	}
	
	public List<String> getAllSets(Joueur joueur) {
		return this.match.getSets(joueur);
	}
	
	public String getScore() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.joueur1.getName());
		sb.append("/");
		sb.append(this.joueur2.getName());
		sb.append(": ");
		sb.append(this.match);
		if (isGameOver()) {
			return sb.toString();
		}
		sb.append(" ");
		sb.append(this.set);
		sb.append(" ");
		if (state instanceof TieBreakImpl) {
			sb.append(" Tie-break : ");
			sb.append(this.tieBreak);
		} else {
			if (state instanceof DeuceImpl) {
				sb.append(this.deuce);
			} else {
				sb.append(this.game);
			}
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return this.getScore();
	}

	public void setState(State state) {
		this.state = state;		
	}

	public State getDeuce() {
		return this.deuce;
	}

	public State getGame() {
		return this.game;
	}

	public State getSet() {
		return this.set;
	}
	
	public State getTieBreak() {
		return this.tieBreak;
	}
	
	public State getMatch() {
		return this.match;
	}

	public Joueur getJoueur1() {
		return joueur1;
	}
	
	public Joueur getJoueur2() {
		return joueur2;
	}
}
