package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private List<Card> hand; 

    public Dealer() {
        hand = new ArrayList<>();
    }

    public void addToHand(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
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

    public boolean isBlackjack(List<Card> hand) {
        return getValue(hand) == 21 && hand.size() == 2;
    }

    public boolean isBust(List<Card> hand) {
        return getValue(hand) > 21;
    }

    
}
