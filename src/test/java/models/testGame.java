package models;

import com.google.common.annotations.VisibleForTesting;
import org.junit.Test;

import java.util.Arrays;
import java.util.EmptyStackException;

import static org.junit.Assert.*;

public class testGame {

    @Test
    public void testGameCreation(){
        Game g = new Game();
        assertNotNull(g);
    }

    @Test
    public void testGameStart(){
        Game g = new Game(GameType.Standard);
        g.resetDeck();
        g.shuffle();
        g.dealFour();
        assertEquals(1,g.cols.get(0).cards.size());
        assertEquals(1,g.cols.get(1).cards.size());
        assertEquals(1,g.cols.get(2).cards.size());
        assertEquals(1,g.cols.get(3).cards.size());
    }

    @Test
    public void testGameDealEmpty(){
        Game g = new Game(GameType.Standard);
        g.resetDeck();
        g.shuffle();
        for(int i = 0; i < 13; i++){
            g.dealFour();
        }
        assertEquals(0, g.deck.theDeck.size());
        g.dealFour();
        assertTrue(g.gameOver);
    }

    @Test
    public void testGameRemove(){
        Game g = new Game(GameType.Standard);
        g.resetDeck();
        g.dealFour();
        assertEquals("14Spades", g.cols.get(0).readTopCard().toString());
        boolean removeFailed = false;
        try {
            g.remove(0);
        }
        catch (Error e){
            removeFailed = true;
        }
        assertTrue(removeFailed);
        Card c = new Card(3, Suit.Spades);
        g.cols.get(1).addCardToCol(c);
        assertEquals("3Spades", g.cols.get(1).readTopCard().toString());
        boolean removePassed = false;
        try {
            g.remove(1);
            removePassed = true;
        }
        catch (Error e){
            removePassed = false;
        }
        assertTrue(removePassed);
    }

    @Test
    public void testGameMoveSuccess() {
        Game g = new Game(GameType.Standard);
        //Test for working case
        Card c = new Card(14, Suit.Spades);
        g.cols.get(0).addCardToCol(c);
        assertEquals("14Spades", g.cols.get(0).readTopCard().toString());
        g.move(0, 1);
        assertEquals("14Spades", g.cols.get(1).readTopCard().toString());
    }

    @Test
    public void testGameMoveFail(){
        Game g = new Game(GameType.Standard);
        //Test for fail case
        Card c = new Card(3, Suit.Spades);
        g.cols.get(0).addCardToCol(c);
        assertEquals("3Spades", g.cols.get(0).readTopCard().toString());
        g.move(0,1);
        assertEquals("3Spades", g.cols.get(0).readTopCard().toString());
    }

    @Test
    public void testStandardGameCheckEnd(){
        Game g = new Game(GameType.Standard);
        g.resetDeck();
        assertFalse(g.check_end());
        g.dealFour();
        assertEquals("14Spades", g.cols.get(0).readTopCard().toString());
        assertEquals("14Diamonds", g.cols.get(1).readTopCard().toString());
        assertEquals("14Hearts", g.cols.get(2).readTopCard().toString());
        assertEquals("14Clubs", g.cols.get(3).readTopCard().toString());
        g.score = 48;
        assertTrue(g.check_end());
    }
	
	@Test
	public void testSpanishGameCheckEnd(){
        Game g = new Game(GameType.Spanish);
        g.resetDeck();
        assertFalse(g.check_end());
        //g.dealFour();
		Card c0 = new Card(14, Suit.Espadas);
		Card c1 = new Card(14, Suit.Copas);
		Card c2 = new Card(14, Suit.Oros);
		Card c3 = new Card(14, Suit.Bastos);
        g.cols.get(0).addCardToCol(c0);
        g.cols.get(1).addCardToCol(c1);
        g.cols.get(2).addCardToCol(c2);
        g.cols.get(3).addCardToCol(c3);
        assertEquals("14Espadas", g.cols.get(0).readTopCard().toString());
        assertEquals("14Copas", g.cols.get(1).readTopCard().toString());
        assertEquals("14Oros", g.cols.get(2).readTopCard().toString());
        assertEquals("14Bastos", g.cols.get(3).readTopCard().toString());
        g.score = 46;
        assertTrue(g.check_end());
    }

}
