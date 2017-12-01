package models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testColumn {

    @Test
    public void testColumnHasCards(){
        Game g = new Game(GameType.Standard);
        g.deck.buildDeck();
        for(int i = 0; i < 4; i++)
            assertEquals(false,g.cols.get(i).columnHasCards());
        g.dealFour();
        for(int i = 0; i < 4; i++)
            assertEquals(true,g.cols.get(i).columnHasCards());
    }

    @Test
    public void testColumnReadTopCard(){
        Game g = new Game(GameType.Standard);
        g.deck.buildDeck();
        g.dealFour();
        //Know that based on an unshuffled deck the top four cards should be the aces
        assertEquals("14Spades", g.cols.get(0).readTopCard().toString());
        assertEquals("14Diamonds", g.cols.get(1).readTopCard().toString());
        assertEquals("14Hearts", g.cols.get(2).readTopCard().toString());
        assertEquals("14Clubs", g.cols.get(3).readTopCard().toString());
        for(int i = 0; i < 12; i++){
            g.dealFour();
        }
        //Know the last cards dealt should be the 2's
        assertEquals("2Spades", g.cols.get(0).readTopCard().toString());
        assertEquals("2Diamonds", g.cols.get(1).readTopCard().toString());
        assertEquals("2Hearts", g.cols.get(2).readTopCard().toString());
        assertEquals("2Clubs", g.cols.get(3).readTopCard().toString());
    }

    @Test
    public void testColumnAddCardToColumn(){
        Game g = new Game(GameType.Standard);
        g.deck.buildDeck();
        g.dealFour();
        //Know based on the unsorted deck that the first four cards dealt are aces
        assertEquals("14Spades", g.cols.get(0).readTopCard().toString());
        assertEquals("14Diamonds", g.cols.get(1).readTopCard().toString());
        Card cardToMove = g.cols.get(0).readTopCard();
        g.cols.get(1).addCardToCol(cardToMove);
        assertEquals("14Spades", g.cols.get(1).readTopCard().toString());
    }

    @Test
    public void testColumnRemoveCardFromColumn(){
        Game g = new Game(GameType.Standard);
        g.deck.buildDeck();
        g.dealFour();
        assertEquals("14Spades", g.cols.get(0).readTopCard().toString());
        //Know based on the unsorted deck that the first four cards dealt are aces
        g.dealFour();
        //Same logic should have kings
        assertEquals("13Spades", g.cols.get(0).readTopCard().toString());
        g.cols.get(0).removeCardFromCol();
        assertEquals("14Spades", g.cols.get(0).readTopCard().toString());
    }
}
