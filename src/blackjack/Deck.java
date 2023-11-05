package blackjack;

import java.util.*;

public class Deck {

    public static final String[] SUITS = {"Spades", "Hearts", "Clubs", "Diamond"};
    public static final String[] RANK = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    public static final Integer[] VALUE = {2, 3, 4, 5, 6 ,7 ,8 ,9, 10, 10, 10, 10, 11};

    private List<Card> cards;
    
    public Deck () {
        cards = new ArrayList<Card>();
        generateNewDeck();
    }


    // METHODS
    private void generateNewDeck() {
        for (int i = 0; i < SUITS.length; i++) {
            for (int j = 0; j < RANK.length; j++) {
                Card card = new Card(SUITS[i], RANK[j], VALUE[j]);
                cards.add(card);
            }
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
        // System.out.println(cards);
    }

    public Card drawCard() {
        Card drawncard = cards.remove(0);
        return drawncard;
    }

}
