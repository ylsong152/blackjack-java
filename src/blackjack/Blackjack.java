package blackjack;

import java.util.*;

public class Blackjack {
    private Deck deck;
    private List<Card> dealerHand;
    private List<Card> playerHand;
    
    private void startGame() {
        deck = new Deck();
        deck.shuffleDeck();

        dealerHand = new ArrayList<>();
        playerHand = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)){
            dealInitial();
            evaluateHand(playerHand);
            playerTurn(scanner);
        }
    } 
    

    private Integer getValue(List<Card> hand) {
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

    // If more players, can place all players into a list and loop through
    private void dealInitial() {
        playerHand.add(deck.drawCard());
        dealerHand.add(deck.drawCard());
        playerHand.add(deck.drawCard());
        dealerHand.add(deck.drawCard());
    }

    private boolean isBlackjack(List<Card> hand) {
        return getValue(hand) == 21 && hand.size() == 2;
    }

    private void evaluateHand(List<Card> hand) {
        System.out.println("Your hand: " + playerHand + "\nTotal value: " + getValue(playerHand));
        if (isBlackjack(playerHand)) {
            System.out.println("BOOM! BLACKJACK!!!");
        } else {
            System.out.println("Do you want to hit or stand?");
        }
    }

    private void playerTurn(Scanner scanner) {
        while (true) {
            System.out.println("Do you want to hit or stand? (h/s): ");
            String playerChoice = scanner.nextLine().trim().toLowerCase();
            if ("h".equals(playerChoice)) {
                playerHand.add(deck.drawCard());
                evaluateHand(playerHand);
                if (getValue(playerHand) > 21) {
                    System.out.println("Bust! Game over.");
                    break;
                }
            } else if ("s".equals(playerChoice)) {
                System.out.println("You stand with: " + getValue(playerHand));
                dealerTurn();
                break;
            } else {
                System.out.println("Invalid input, please try again.");
            }
        }
    }

    private void dealerTurn() {
        System.out.println(getValue(dealerHand));
    }
    

    // Maybe can implement a method for printing player hand to remove the [ ]

    public static void main(String[] args) {

        Blackjack pog = new Blackjack();
        pog.startGame();
    }        
}
