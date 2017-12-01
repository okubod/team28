package models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testSpanishDeck {

    @Test
    public void testBuildDeck(){
        Game g = new Game(GameType.Spanish);
        g.deck.buildDeck();
        assertEquals(50,g.deck.theDeck.size());
    }
}
