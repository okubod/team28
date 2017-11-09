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
                cols.get(i).add(deck.draw());
            }
        }
        catch (EmptyStackException e){
            //call end game function
            System.out.println("Out of cards");
        }
    }

    /*
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
        //call column remove
    }


    public void move(int columnFrom, int columnTo) {
        Card cardToMove = getTopCard(columnFrom);
        this.removeCardFromCol(columnFrom);
        this.addCardToCol(columnTo,cardToMove);
    }

    private void addCardToCol(int columnTo, Card cardToMove) {
        cols.get(columnTo).add(cardToMove);
    }

    private void removeCardFromCol(int colFrom) {
        this.cols.get(colFrom).remove(this.cols.get(colFrom).size()-1);
    }
}