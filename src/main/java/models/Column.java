package models;

import java.util.ArrayList;

public class Column {

    public java.util.List<Card> cards = new ArrayList<>();

    public void remove(int columnNumber) {
        if(columnHasCards()) {
            Card c = getTopCard();
            boolean removeCard = false;
            for (int i = 0; i < 4; i++) {
                if (i != columnNumber) {
                    if (columnHasCards()) {
                        Card compare = getTopCard();
                        if (compare.getSuit() == c.getSuit()) {
                            if (compare.getValue() > c.getValue()) {
                                removeCard = true;
                            }
                        }
                    }
                }
            }
            if (removeCard) {
                this.cards.remove(this.cards.size() - 1);
            }
            else {
                throw new Error("Invalid remove");
            }
        }
    }

    private boolean columnHasCards() {
        if(this.cards.size()>0){
            return true;
        }
        return false;
    }

    private Card getTopCard() {
        return this.cards.get(this.cards.size()-1);
    }

}
