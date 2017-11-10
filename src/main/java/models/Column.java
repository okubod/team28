package models;

import java.util.ArrayList;

public class Column {

    public java.util.List<Card> cards = new ArrayList<>();

    public boolean columnHasCards() {
        if(this.cards.size()>0){
            return true;
        }
        return false;
    }

    public Card getTopCard() {
        return this.cards.get(this.cards.size()-1);
    }

    public void addCardToCol(Card cardToMove) {
        cards.add(cardToMove);
    }

    public void removeCardFromCol() {
        this.cards.remove(this.cards.size()-1);
    }

}
