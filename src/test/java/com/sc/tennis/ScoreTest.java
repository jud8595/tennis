package com.sc.tennis;

import org.junit.Assert;
import org.junit.Test;

import com.sc.tennis.graph.Score;
import com.sc.tennis.graph.impl.ScoreImpl;
import com.sc.tennis.joueur.Joueur;
import com.sc.tennis.joueur.impl.JoueurImpl;

public class ScoreTest {
	
	@Test
    public void testScore() {
        Joueur j1 = new JoueurImpl("S. Wawrinka");
        Joueur j2 = new JoueurImpl("N. Djokovic");
        
        Score score = new ScoreImpl(j1, j2);
        score.increment(j1);
        score.increment(j1);
        score.increment(j2);
        score.increment(j1);
        
        Assert.assertTrue(score.getCurrentGame(j1).equals("40") && score.getCurrentGame(j2).equals("15"));
        
        score.increment(j2);
        score.increment(j2);
        
        Assert.assertTrue(score.isPlayingDeuce() && score.isDeuce());
        
        score.increment(j2);
        
        Assert.assertTrue(score.isAdvantage(j2));
        
        score.increment(j2);
        
        Assert.assertTrue(!score.isPlayingDeuce() && score.getCurrentSet(j2).equals("1"));
        
        score.increment(j1);
        score.increment(j1);
        score.increment(j1);
        score.increment(j1);
        
        Assert.assertTrue(score.getCurrentSet(j1).equals("1"));
        
        for (int i=0; i<19; i++) {
        	score.increment(j1);
        }
        for (int i=0; i<21; i++) {
        	score.increment(j2);
        }
        for (int i=0; i<4; i++) {
        	score.increment(j1);
        }
        
        Assert.assertTrue(score.isPlayingTieBreak());
        
        for (int i=0; i<6; i++) {
        	score.increment(j1);
        }
        for (int i=0; i<4; i++) {
        	score.increment(j2);
        }
        
        Assert.assertTrue(score.getCurrentTieBreak(j1).equals("6"));
        Assert.assertTrue(score.getCurrentTieBreak(j2).equals("4"));

        score.increment(j1);
        
        Assert.assertTrue(score.getAllSets(j1).get(0).equals("7"));
        Assert.assertTrue(score.getAllSets(j2).get(0).equals("6"));
        
        for (int i=0; i<72; i++) {
        	score.increment(j2);
        }
        
        Assert.assertTrue(score.isGameOver());
        
        System.out.println(score.getScore());
    }
}
