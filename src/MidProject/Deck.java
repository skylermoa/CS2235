package MidProject;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    // Instance Variables
    private final ArrayList<Card> cards = new ArrayList<>();

    // Constructors
    public Deck() {
        for (Suit suit : Suit.values()) { // Cycle through Suit values
            if (suit.getName().equals("None")) { // Make sure Suit isn't a Joker
                continue;
            }
            for (Rank rank : Rank.values()) { // Cycle through Rank values
                if (rank.getName().equals("Joker")){ // Make sure Rank isn't a Joker
                    continue;
                }
                cards.add(new Card(rank, suit)); // Add a new Card
            }
        }
        cards.add(new Card(Rank.JOKER, Suit.NONE)); // Add a Joker
        cards.add(new Card(Rank.JOKER, Suit.NONE)); // Add another Joker
        Collections.shuffle(this.cards);
    }

    // Getters
    public ArrayList<Card> getCards() {
        return cards;
    }

    // Other Methods
    public Card dealCard() {
        return this.cards.remove(0); // Returns and removes the first Card in Deck
    }

    // toString Method
    public String toString() {
        return this.cards.toString(); // Show each Card in Deck
    }

}

