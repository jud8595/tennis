package com.sc.tennis.graph;

import java.util.List;

import com.sc.tennis.joueur.Joueur;

public interface Score {

	public void increment(Joueur joueur);
	
	public boolean isGameOver();
	
	public String getCurrentGame(Joueur joueur);
	
	public boolean isPlayingDeuce();
	
	public boolean isPlayingTieBreak();
	
	public boolean isDeuce();
	
	public boolean isAdvantage(Joueur joueur);
	
	public String getCurrentSet(Joueur joueur);
	
	public String getCurrentTieBreak(Joueur joueur);
	
	public List<String> getAllSets(Joueur joueur);
	
	public void setState(State state);
	
	public String getScore();
	
	public State getDeuce();

	public State getGame();
	
	public State getSet();

	public State getTieBreak();

	public State getMatch();
	
	public Joueur getJoueur1();
	
	public Joueur getJoueur2();
}
