package models;

public class SpanishDeck extends Deck{

    @Override
    public void buildDeck() {
        theDeck.clear();
        //set up numbered cards 1-12 for each suit
        for(int i = 2; i < 13; i++){
            theDeck.add(new Card(i,Suit.Bastos));
            theDeck.add(new Card(i,Suit.Oros));
            theDeck.add(new Card(i,Suit.Copas));
            theDeck.add(new Card(i,Suit.Espadas));
        }
        //set up aces
        theDeck.add(new Card(14,Suit.Bastos));
        theDeck.add(new Card(14,Suit.Oros));
        theDeck.add(new Card(14,Suit.Copas));
        theDeck.add(new Card(14,Suit.Espadas));
        //set up two jokers
        for(int i = 0; i < 2; i++) {
            theDeck.add(new Card(15, Suit.Joker));
        }
    }
}
