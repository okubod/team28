package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Random;

public class Deck {

    public java.util.List<Card> theDeck = new ArrayList<>();

    public void buildDeck() {
        theDeck.clear();
        for(int i = 2; i < 15; i++){
            theDeck.add(new Card(i,Suit.Clubs));
            theDeck.add(new Card(i,Suit.Hearts));
            theDeck.add(new Card(i,Suit.Diamonds));
            theDeck.add(new Card(i,Suit.Spades));
        }
    }

    public void shuffle() {
        long seed = System.nanoTime();
        Collections.shuffle(theDeck, new Random(seed));
    }

    public Card draw() {
        if (theDeck.size() == 0) {
            //if this is true then game should end
            throw new EmptyStackException();
        }
        else{
            Card temp = theDeck.remove(theDeck.size() - 1);
            return temp;
        }
    }
}
