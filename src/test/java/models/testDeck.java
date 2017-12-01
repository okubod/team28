package models;

import org.junit.Test;

import java.util.Arrays;
import java.util.EmptyStackException;

import static org.junit.Assert.*;

public class testDeck {

    @Test
    public void testBuildDeck(){
        Game g = new Game(GameType.Standard);
        assertEquals(0, g.deck.theDeck.size());
        g.deck.buildDeck();
        assertEquals(52,g.deck.theDeck.size());
    }

    @Test
    public void testDeckShuffle(){
        Game g1 = new Game(GameType.Standard);
        g1.deck.buildDeck();
        Game g2 = new Game(GameType.Standard);
        g2.deck.buildDeck();

        g1.deck.shuffle();
        g2.deck.shuffle();
        assertFalse(Arrays.equals(g1.deck.theDeck.toArray(),g2.deck.theDeck.toArray()));
    }

    @Test
    public void testDeckDrawEmpty(){
        Game g = new Game(GameType.Standard);
        g.deck.buildDeck();
        for(int i = 0; i < 52; i++){
            g.deck.draw();
        }
        assertEquals(0,g.deck.theDeck.size());
        boolean exceptionOccured = false;
        try {
            //Forcing exception to occur
            g.deck.draw();
        }
        catch (EmptyStackException e){
            exceptionOccured = true;
        }
        assertTrue(exceptionOccured);
    }

    @Test
    public void testDeckDrawRemoves(){
        Game g = new Game(GameType.Standard);
        g.deck.buildDeck();
        int previousSize = g.deck.theDeck.size();
        for(int i = 0; i < 52; i++){
            boolean sizeCorrect = false;
            g.deck.draw();
            if((previousSize-1) == g.deck.theDeck.size()){
                sizeCorrect = true;
                previousSize = previousSize-1;
            }
            assertTrue(sizeCorrect);
        }
    }

}
