package com.sc.tennis.graph;

import java.util.List;

import com.sc.tennis.joueur.Joueur;

public interface Match extends State {

	public List<String> getSets(Joueur joueur);
}
