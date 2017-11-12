package models;


import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Random;

/**
 * Assignment 1: Students must implement dealFour(), remove(), move(), and columnHasCards() methods
 *
 * The customDeal() method is not present in the Assignment1_Student version since tests (and the test dir) are removed
 * to prevent confusion regarding testing and the use of unit tests; testing is covered more thoroughly in CS362.
 */
public class Game {

    public int score = 0;
    public boolean gameOver = false;
    public boolean victory = false;
    public Deck deck;
    public java.util.List<Column> cols = new ArrayList<>();


    public Game(){
        for (int i = 0; i < 4; i++){
            cols.add(new Column());
        }
        deck = new Deck();
    }

    public void resetDeck() {
        deck.buildDeck();
    }

    public void shuffle() {

        deck.shuffle();

    }

    public void dealFour() {
        try {
            for (int i = 0; i < 4; i++) {
                cols.get(i).addCardToCol(deck.draw());
            }
        }
        catch (EmptyStackException e){
			gameOver = true;
			victory = check_end();
			
        }
    }

    /*Commented out as okay'd by Dr. Sarma
    //customDeal to setup game for testing purposes
    public void customDeal(int c1, int c2, int c3, int c4) {
        cols.get(0).add(deck.get(c1));
        deck.remove(c1);
        cols.get(1).add(deck.get(c2));
        deck.remove(c2);
        cols.get(2).add(deck.get(c3));
        deck.remove(c3);
        cols.get(3).add(deck.get(c4));
        deck.remove(c4);
    }
    */

    public void remove(int columnNumber) {
        //System.out.println(columnNumber);
        if(cols.get(columnNumber).columnHasCards()) {
            Card c = cols.get(columnNumber).readTopCard();
            boolean removeCard = false;
            for (int i = 0; i < 4; i++) {
                if (i != columnNumber) {
                    if (cols.get(i).columnHasCards()) {
                        Card compare = cols.get(i).readTopCard();
                        if (compare.getSuit() == c.getSuit()) {
                            if (compare.getValue() > c.getValue()) {
                                removeCard = true;
                            }
                        }
                    }
                }
            }
            if (removeCard) {

                score = score + 1;

                this.cols.get(columnNumber).removeCardFromCol();

            }
            else {
                throw new Error("Invalid remove");
            }
        }
        //System.out.println("Score : " + score);
    }

    public void move(int columnFrom, int columnTo) {
        if(cols.get(columnTo).columnHasCards() == false) {
            Card cardToMove = cols.get(columnFrom).readTopCard();
            if(cardToMove.getValue() == 14) {
                cols.get(columnFrom).removeCardFromCol();
                cols.get(columnTo).addCardToCol(cardToMove);
            }
        }
    }
	
	public boolean check_end(){
		int aceCount = 0;
		if(score == 48){
			for(int i=0;i<4;i++){
				if(cols.get(i).columnHasCards() == true){
					if(cols.get(i).readTopCard().getValue() == 14){
						aceCount++;
					}
				}	
			}
		}
		if(aceCount == 4){
			return true;
		}
		else{
			return false;
		}
	}

}