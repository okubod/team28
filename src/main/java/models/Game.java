package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Assignment 1: Each of the blank methods below require implementation to get AcesUp to build/run
 */
public class Game {

    public java.util.List<Card> deck = new ArrayList<>();

    public java.util.List<java.util.List<Card>> cols = new ArrayList<>(4);


    public Game(){
        // initialize a new game such that each column can store cards
		for (int i=0; i<4; i++){
			cols.add(new ArrayList<Card>());
		}
		
    }

    public void buildDeck() {
        for(int i = 2; i < 15; i++){
            deck.add(new Card(i,Suit.Clubs));
            deck.add(new Card(i,Suit.Hearts));
            deck.add(new Card(i,Suit.Diamonds));
            deck.add(new Card(i,Suit.Spades));
        }
    }

    public void shuffle() {
        // shuffles the deck so that it is random
        Random rand = new Random();
        for(int i = 0; i < 10000; i++) {
            int x = rand.nextInt(52);
            int y = rand.nextInt(52);
            if (x==y) continue;
            Card temp = deck.get(x);
            deck.set(x, deck.get(y));
            deck.set(y, temp);
        }
        // Checking shuffle
        // for(int i = 0; i < 52; i++){
        //    System.out.println(deck.get(i).suit + " " + deck.get(i).value);
        //}
    }
    
    public void dealFour() {
        // remove the top card from the deck and add it to a column; repeat for each of the four columns
        for(int i=0; i < 4; i++){
            cols.get(i).add(deck.get(deck.size()-1));
            deck.remove(deck.size()-1);
        }
    }

    public void remove(int columnNumber) {
        //remove the top card from the indicated column
		if (columnHasCards(columnNumber) == true) {
            for (int i = 0; i < 4; i++) {
                if (i != columnNumber && getTopCard(columnNumber).suit == getTopCard(i).suit && getTopCard(columnNumber).value < getTopCard(i).value) {
                    removeCardFromCol(columnNumber);
                }
            }


        }
    }
    

    private boolean columnHasCards(int columnNumber) {
        // check indicated column for number of cards; if no cards return false, otherwise return true
        if(cols.get(columnNumber).size() > 0) {
            return true;
        }
        return false;
    }

    private Card getTopCard(int columnNumber) {
        return this.cols.get(columnNumber).get(this.cols.get(columnNumber).size()-1);
    }


    public void move(int columnFrom, int columnTo) {
		//check that the original column (columnFrom) is not empty
		if (columnHasCards(columnTo)==false){
			//copies card currently at top of the columnFrom column to the columnTo column
			addCardToCol(columnTo, this.getTopCard(columnFrom));
			//removes original card that was copied to the new column
			removeCardFromCol(columnFrom); 
		}
		else{
			System.out.println("There is no card to move in that column.");
		}
        
		
    }

    private void addCardToCol(int columnTo, Card cardToMove) {
        cols.get(columnTo).add(cardToMove);
    }

    private void removeCardFromCol(int colFrom) {
        this.cols.get(colFrom).remove(this.cols.get(colFrom).size()-1);
    }
}
