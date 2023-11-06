package blackjack;

import java.util.*;

public class Player {
    private List<Card> hand; 

    public Player() {
        hand = new ArrayList<>();
    }

    public Integer getValue(List<Card> hand) {
        Integer sum = 0;
        Integer aceCount = 0;
        for (Card card : hand) {
            sum += card.getValue();
            if (card.getRank().equals("A")) {
                aceCount++;
            }
        }
        // Using while rather than if here as need to constantly check for over 21
        while (sum > 21 && aceCount > 0) {
            sum -= 10;
            aceCount--;
        }
        return sum;
    }

    // Player should be able to hit/stand and get the value of their hand
    public void addToHand(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public boolean isBlackjack(List<Card> hand) {
        return getValue(hand) == 21 && hand.size() == 2;
    }

    public boolean isBust(List<Card> hand) {
        return getValue(hand) > 21;
    }
}
